package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.entity.TableEntity;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {
    public static TableEntity toEntity(TableRequest request) {
        if (request == null) return null;

        return TableEntity.builder()
                .name(request.getName())
                .qrCode(request.getQrCode())
                .status(request.getStatus())
                .build();
    }

    // Entity → Response
    public static TableResponse toResponse(TableEntity entity) {
        if (entity == null) return null;

        return TableResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .qrCode(entity.getQrCode())
                .status(entity.getStatus())
                .shopId(entity.getShop().getId())
                .shopName(entity.getShop().getName())
                .build();
    }
}