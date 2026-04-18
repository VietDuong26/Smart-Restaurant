package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.RoleRequest;
import com.example.SmartRestaurant.dto.response.RoleResponse;
import com.example.SmartRestaurant.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleEntity toEntity(RoleRequest request) {
        if (request == null) return null;

        return RoleEntity.builder()
                .name(request.getName())
                .build();
    }

    public RoleResponse toResponse(RoleEntity entity) {
        if (entity == null) return null;

        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .permissions(
                        entity.getPermissions() == null ? null :
                                entity.getPermissions().stream()
                                        .map(p -> p.getName())
                                        .toList()
                )
                .build();
    }
}