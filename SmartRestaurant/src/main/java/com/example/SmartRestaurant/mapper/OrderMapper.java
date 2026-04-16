package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.OrderRequest;
import com.example.SmartRestaurant.dto.response.OrderResponse;
import com.example.SmartRestaurant.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    // Request → Entity
    public static OrderEntity toEntity(OrderRequest request) {
        if (request == null) return null;

        return OrderEntity.builder()
                .status(request.getStatus())
                .build();
    }

    public static OrderResponse toResponse(OrderEntity entity) {
        if (entity == null) return null;

        return OrderResponse.builder()
                .id(entity.getId())

                .tableId(entity.getTable().getId())
                .tableName(entity.getTable().getName())

                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .userName(entity.getUser() != null ? entity.getUser().getName() : null)

                .status(entity.getStatus())
                .totalAmount(entity.getTotalAmount())
                .createdTime(entity.getCreatedTime())

                .items(
                        entity.getItems() == null ? null :
                                entity.getItems().stream()
                                        .map(OrderItemMapper::toResponse)
                                        .toList()
                )
                .build();
    }
}