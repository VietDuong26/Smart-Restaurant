package com.example.SmartRestaurant.service.product;

import com.example.SmartRestaurant.common.ProductStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ProductRequest;
import com.example.SmartRestaurant.dto.response.ProductResponse;
import com.example.SmartRestaurant.entity.CategoryEntity;
import com.example.SmartRestaurant.entity.ProductEntity;
import com.example.SmartRestaurant.exception.CategoryNotFoundException;
import com.example.SmartRestaurant.exception.ProductNotFoundException;
import com.example.SmartRestaurant.mapper.ProductMapper;
import com.example.SmartRestaurant.repository.CategoryRepository;
import com.example.SmartRestaurant.repository.ProductRepository;
import com.example.SmartRestaurant.util.validateownership.OwnerShipValidateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository repository;
    ProductMapper mapper;
    OwnerShipValidateService ownerShipValidateService;
    CategoryRepository categoryRepository;


    @Override
    @Transactional
    public ProductResponse create(ProductRequest productRequest, CustomUserDetails userDetails) {
        CategoryEntity category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(category.getShop(), userDetails);
        }
        ProductEntity product = mapper.toEntity(productRequest);
        product.setCreatedAt(LocalDateTime.now());
        product.setCategory(category);
        product.setStatus(ProductStatus.AVAILABLE);
        ProductEntity savedProduct = repository.save(product);
        return mapper.toResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest, CustomUserDetails userDetails) {
        ProductEntity product = repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(product.getCategory().getShop(), userDetails);
        }
        if (productRequest.getName() != null) {
            product.setName(productRequest.getName().isEmpty()
                    ? null
                    : productRequest.getName());
        }
        if (productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription().isEmpty()
                    ? null
                    : productRequest.getDescription());
        }
        if (productRequest.getImageUrl() != null) {
            product.setImageUrl(productRequest.getImageUrl().isEmpty()
                    ? null
                    : productRequest.getImageUrl());
        }
        if (productRequest.getImagePublicId() != null) {
            product.setImagePublicId(productRequest.getImagePublicId().isEmpty()
                    ? null
                    : productRequest.getImagePublicId());
        }
        if (productRequest.getPrice() != null) {
            product.setPrice(productRequest.getPrice());
        }
        if (productRequest.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(category);
        }

        product.setUpdatedAt(LocalDateTime.now());


        ProductEntity savedProduct = repository.save(product);
        return mapper.toResponse(savedProduct);
    }

    @Override
    public void delete(Long id, CustomUserDetails userDetails) {

    }

    @Override
    public ProductResponse getById(Long id, CustomUserDetails userDetails) {
        return null;
    }

    @Override
    public List<ProductResponse> getAll(CustomUserDetails userDetails) {
        return null;
    }
}
