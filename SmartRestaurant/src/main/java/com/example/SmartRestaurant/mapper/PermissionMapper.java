package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.PermissionRequest;
import com.example.SmartRestaurant.dto.response.PermissionResponse;
import com.example.SmartRestaurant.entity.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public PermissionEntity toEntity(PermissionRequest request) {
        if (request == null) return null;

        return PermissionEntity.builder()
                .name(request.getName())
                .build();
    }

    public PermissionResponse toResponse(PermissionEntity entity) {
        if (entity == null) return null;

        return PermissionResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}