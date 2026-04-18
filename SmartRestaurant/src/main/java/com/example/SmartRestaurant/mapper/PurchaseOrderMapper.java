package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.PurchaseOrderRequest;
import com.example.SmartRestaurant.dto.response.PurchaseOrderResponse;
import com.example.SmartRestaurant.entity.PurchaseOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PurchaseOrderMapper {
    PurchaseOrderItemMapper purchaseOrderItemMapper;

    public PurchaseOrderEntity toEntity(PurchaseOrderRequest request) {
        if (request == null) return null;

        return PurchaseOrderEntity.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Entity → Response
    public PurchaseOrderResponse toResponse(PurchaseOrderEntity entity) {
        if (entity == null) return null;

        return PurchaseOrderResponse.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())

                .shopId(entity.getShop().getId())
                .shopName(entity.getShop().getName())

                .supplierId(entity.getSupplier().getId())
                .supplierName(entity.getSupplier().getName())

                .items(
                        entity.getItems() == null ? null :
                                entity.getItems().stream()
                                        .map(purchaseOrderItemMapper::toResponse)
                                        .toList()
                )
                .build();
    }
}