package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.ProductRequest;
import com.example.SmartRestaurant.dto.response.ProductResponse;
import com.example.SmartRestaurant.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity toEntity(ProductRequest request) {
        if (request == null) return null;

        return ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .status(request.getStatus())
                .build();
    }

    public ProductResponse toResponse(ProductEntity product) {
        if (product == null) return null;

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .status(product.getStatus())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
