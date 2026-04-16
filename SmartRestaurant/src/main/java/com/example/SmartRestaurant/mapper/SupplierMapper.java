package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.SupplierRequest;
import com.example.SmartRestaurant.dto.response.SupplierResponse;
import com.example.SmartRestaurant.entity.SupplierEntity;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public static SupplierEntity toEntity(SupplierRequest request) {
        if (request == null) return null;

        return SupplierEntity.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .status(request.getStatus())
                .build();
    }

    // Entity → Response
    public static SupplierResponse toResponse(SupplierEntity entity) {
        if (entity == null) return null;

        return SupplierResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .status(entity.getStatus())
                .shopId(entity.getShop().getId())
                .shopName(entity.getShop().getName())
                .build();
    }
}