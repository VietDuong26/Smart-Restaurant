package com.example.SmartRestaurant.service.inventorydocument;

import com.example.SmartRestaurant.common.enums.InventoryDocumentStatus;
import com.example.SmartRestaurant.dto.request.InventoryDocumentApproveRequest;
import com.example.SmartRestaurant.dto.request.InventoryDocumentRejectRequest;
import com.example.SmartRestaurant.dto.request.InventoryDocumentRequest;
import com.example.SmartRestaurant.dto.response.InventoryDocumentResponse;
import com.example.SmartRestaurant.entity.*;
import com.example.SmartRestaurant.exception.InsufficientStockException;
import com.example.SmartRestaurant.exception.InvalidStatusException;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.ValidateException;
import com.example.SmartRestaurant.mapper.InventoryDocumentMapper;
import com.example.SmartRestaurant.repository.*;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    RecipeRepository recipeRepository;

    OrderItemRepository orderItemRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public InventoryDocumentResponse create(Long shopId, InventoryDocumentRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));

        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_INVENTORY_CREATE");
        validateRequest(request);
        InventoryDocumentEntity documentEntity = mapper.toEntity(request);
        documentEntity.setCreatedBy(currentUserProvider.getCurrentUser().getUser());
        documentEntity.setStatus(InventoryDocumentStatus.PENDING);
        documentEntity.setShop(shop);
        InventoryDocumentEntity savedDocument = repository.save(documentEntity);
        savedDocument.setItems(documentItemService.createAll(savedDocument, request.getItems()));
        return mapper.toResponse(savedDocument);
    }

    @Override
    public InventoryDocumentResponse approve(Long shopId, InventoryDocumentApproveRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Của hàng"));

        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_INVENTORY_APPROVE");
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
                    switch (ingredient.getType()) {
                        case DRY -> {
                            ingredient.setCurrentStock(ingredient.getCurrentStock().add(x.getQuantity()));
                        }
                        case FRESH -> {
                            ingredient.setCurrentStock(ingredient.getCurrentStock()
                                    .add(x.getQuantity().multiply(ingredient.getYieldRate())));
                        }
                    }

                });
                break;
            case EXPORT, WASTE:
                documentEntity.getItems().forEach(x -> {
                    IngredientEntity ingredient = x.getIngredient();
                    if (ingredient.getCurrentStock().compareTo(x.getQuantity()) < 0) {
                        throw new InsufficientStockException("Không đủ tồn kho");
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

        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_INVENTORY_REJECT");
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
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));

        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_INVENTORY_VIEW");
        Page<InventoryDocumentEntity> documentEntities = status == null
                ? repository.findAllByShopId(shopId, pageable)
                : repository.findAllByShopIdAndStatus(shopId, status, pageable);
        return documentEntities.map(x -> mapper.toResponse(x));
    }

    @Override
    public InventoryDocumentResponse getById(Long inventoryDocumentId) {
        InventoryDocumentEntity inventoryDocument = repository.findById(inventoryDocumentId)
                .orElseThrow(() -> new NotFoundException("Phiếu vật tư"));

        authorizationService.checkOwnerOrPermissionInShop(inventoryDocument.getShop(), "PERM_INVENTORY_VIEW");
        return mapper.toResponse(inventoryDocument);
    }

    @Override
    public void deductStockByOrderId(Long orderId) {
        List<OrderItemEntity> orderItems = orderItemRepository.findAllByOrderId(orderId);
        Map<Long, BigDecimal> ingredientAmountMap = new HashMap<>();//lượng nguyên liệu cần dùng từ order
        orderItems.forEach(x -> {
            List<RecipeIngredientEntity> recipeIngredientEntities = recipeRepository.findByProduct_Id(
                    x.getProduct().getId()).getRecipeIngredientEntities();
            recipeIngredientEntities.forEach(y -> {
                ingredientAmountMap.merge(y.getIngredient().getId(),
                        y.getQuantity().multiply(BigDecimal.valueOf(x.getQuantity())),
                        BigDecimal::add
                );
            });
        });
        List<IngredientEntity> ingredientsForUpdate = ingredientRepository.findAllByIdsForUpdate(
                ingredientAmountMap.keySet());
        ingredientsForUpdate.forEach(x -> {
            if (x.getCurrentStock().compareTo(ingredientAmountMap.get(x.getId())) < 0) {
                throw new ValidateException("Lượng tồn kho không đủ để thực hiện");
            }
            x.setCurrentStock(x.getCurrentStock().subtract(ingredientAmountMap.get(x.getId())));
        });
    }

}
