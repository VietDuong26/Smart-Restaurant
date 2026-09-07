package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.StaffOrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    ProductMapper productMapper;
    EmploymentMapper employmentMapper;

    public OrderItemEntity toEntity(StaffOrderItemRequest request) {
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
                .build();
    }
}
