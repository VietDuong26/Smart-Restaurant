package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.response.RoleResponse;
import com.example.SmartRestaurant.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse toResponse(RoleEntity role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
