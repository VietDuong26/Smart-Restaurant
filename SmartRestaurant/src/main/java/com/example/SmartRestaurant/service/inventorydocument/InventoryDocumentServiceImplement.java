package com.example.SmartRestaurant.service.inventorydocument;

import com.example.SmartRestaurant.common.enums.InventoryDocumentStatus;
import com.example.SmartRestaurant.dto.request.InventoryDocumentApproveRequest;
import com.example.SmartRestaurant.dto.request.InventoryDocumentRejectRequest;
import com.example.SmartRestaurant.dto.request.InventoryDocumentRequest;
import com.example.SmartRestaurant.dto.response.InventoryDocumentResponse;
import com.example.SmartRestaurant.entity.IngredientEntity;
import com.example.SmartRestaurant.entity.InventoryDocumentEntity;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.InvalidStatusException;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.OutOfStockException;
import com.example.SmartRestaurant.mapper.InventoryDocumentMapper;
import com.example.SmartRestaurant.repository.InventoryDocumentRepository;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.security.CurrentUserProvider;
import com.example.SmartRestaurant.service.authorization.AuthorizationService;
import com.example.SmartRestaurant.service.inventorydocumentitem.InventoryDocumentItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.SmartRestaurant.validator.InventoryDocumentValidator.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class InventoryDocumentServiceImplement implements InventoryDocumentService {
    InventoryDocumentRepository repository;
    AuthorizationService authorizationService;

    CurrentUserProvider currentUserProvider;
    InventoryDocumentMapper mapper;
    ShopRepository shopRepository;
    InventoryDocumentItemService documentItemService;

    @Override
    public InventoryDocumentResponse create(Long shopId, InventoryDocumentRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        authorizationService.checkOwnerShop(shopId);
        validateRequest(request);
        InventoryDocumentEntity documentEntity = mapper.toEntity(request);
        documentEntity.setCreatedBy(currentUserProvider.getCurrentUser().getUser());
        documentEntity.setStatus(InventoryDocumentStatus.PENDING);
        documentEntity.setShop(shop);
        InventoryDocumentEntity savedDocument = repository.save(documentEntity);
        documentItemService.createAll(savedDocument, request.getItems());
        return mapper.toResponse(savedDocument);
    }

    @Override
    public InventoryDocumentResponse approve(Long shopId, InventoryDocumentApproveRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        authorizationService.checkOwnerShop(shopId);
        validateApproveRequest(request);
        InventoryDocumentEntity documentEntity = repository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Phiếu vật tư"));
        if (!documentEntity.getShop().getId().equals(shopId)) {
            throw new NotFoundException("Phiếu vật tư");
        }
        if (documentEntity.getStatus() != InventoryDocumentStatus.PENDING) {
            throw new InvalidStatusException("phiếu vật tư");
        }
        documentEntity.setStatus(InventoryDocumentStatus.CONFIRMED);
        documentEntity.setReviewedBy(currentUserProvider.getCurrentUser().getUser());
        documentEntity.setReviewedAt(LocalDateTime.now());
        //lấy ra tất cả các ingredient của phiếu
        switch (documentEntity.getType()) {
            case IMPORT:
                documentEntity.getItems().forEach(x -> {
                    IngredientEntity ingredient = x.getIngredient();
                    ingredient.setCurrentStock(ingredient.getCurrentStock().add(x.getQuantity()));
                });
                break;
            case EXPORT, WASTE:
                documentEntity.getItems().forEach(x -> {
                    IngredientEntity ingredient = x.getIngredient();
                    if (ingredient.getCurrentStock().compareTo(x.getQuantity()) < 0) {
                        throw new OutOfStockException("Không đủ tồn kho");
                    }
                    ingredient.setCurrentStock(ingredient.getCurrentStock().subtract(x.getQuantity()));
                });
                break;
        }
        return mapper.toResponse(repository.save(documentEntity));
    }

    @Override
    public InventoryDocumentResponse reject(Long shopId, InventoryDocumentRejectRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        authorizationService.checkOwnerShop(shopId);
        validateRejectRequest(request);
        InventoryDocumentEntity documentEntity = repository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Phiếu vật tư"));
        if (!documentEntity.getShop().getId().equals(shopId)) {
            throw new NotFoundException("Phiếu vật tư");
        }
        if (documentEntity.getStatus() != InventoryDocumentStatus.PENDING) {
            throw new InvalidStatusException("phiếu vật tư");
        }
        documentEntity.setStatus(InventoryDocumentStatus.CANCELLED);
        documentEntity.setReviewedBy(currentUserProvider.getCurrentUser().getUser());
        documentEntity.setReviewedAt(LocalDateTime.now());
        documentEntity.setRejectReason(request.getRejectReason());
        return mapper.toResponse(repository.save(documentEntity));
    }

    @Override
    public Page<InventoryDocumentResponse> getAllByShopId(Long shopId, InventoryDocumentStatus status, Pageable pageable) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        authorizationService.checkOwnerShop(shopId);
        Page<InventoryDocumentEntity> documentEntities = status == null
                ? repository.findAllByShopId(shopId, pageable)
                : repository.findAllByShopIdAndStatus(shopId, status, pageable);
        return documentEntities.map(x -> mapper.toResponse(x));
    }

}
