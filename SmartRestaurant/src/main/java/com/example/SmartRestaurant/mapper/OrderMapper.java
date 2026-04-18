package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.OrderRequest;
import com.example.SmartRestaurant.dto.response.OrderResponse;
import com.example.SmartRestaurant.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    OrderItemMapper orderItemMapper;

    public OrderEntity toEntity(OrderRequest request) {
        if (request == null) return null;

        return OrderEntity.builder()
                .status(request.getStatus())
                .build();
    }

    public OrderResponse toResponse(OrderEntity entity) {
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
                                        .map(orderItemMapper::toResponse)
                                        .toList()
                )
                .build();
    }
}