package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.OrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.entity.OrderItemEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderItemMapper {
    ProductMapper productMapper;

    public OrderItemEntity toEntity(OrderItemRequest request) {
        return OrderItemEntity.builder()
                .quantity(request.getQuantity())
                .note(request.getNote())
                .build();
    }

    public OrderItemResponse toResponse(OrderItemEntity orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .productResponse(productMapper.toResponse(orderItem.getProduct()))
                .orderId(orderItem.getOrder().getId())
                .userId(orderItem.getUser().getId())
                .userName(orderItem.getUser().getName())
                .productName(orderItem.getProductName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .createdAt(orderItem.getCreatedAt())
                .note(orderItem.getNote())
                .build();
    }
}
