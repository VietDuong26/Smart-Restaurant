package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.PurchaseOrderItemRequest;
import com.example.SmartRestaurant.dto.response.PurchaseOrderItemResponse;
import com.example.SmartRestaurant.entity.PurchaseOrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderItemMapper {
    public PurchaseOrderItemEntity toEntity(PurchaseOrderItemRequest request) {
        if (request == null) return null;

        return PurchaseOrderItemEntity.builder()
                .quantity(request.getQuantity())
                .build();
    }

    public PurchaseOrderItemResponse toResponse(PurchaseOrderItemEntity entity) {
        if (entity == null) return null;

        return PurchaseOrderItemResponse.builder()
                .id(entity.getId())
                .ingredientId(entity.getIngredient().getId())
                .ingredientName(entity.getIngredient().getName())
                .ingredientUnit(entity.getIngredient().getUnit())
                .quantity(entity.getQuantity())
                .build();
    }
}