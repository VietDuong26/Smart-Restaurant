package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.OrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public static OrderItemEntity toEntity(OrderItemRequest request) {
        if (request == null) return null;

        return OrderItemEntity.builder()
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .note(request.getNote())
                .build();
    }

    public static OrderItemResponse toResponse(OrderItemEntity entity) {
        if (entity == null) return null;

        return OrderItemResponse.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .productName(entity.getProduct().getName())
                .productImageUrl(entity.getProduct().getImageUrl())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .note(entity.getNote())
                .build();
    }
}
