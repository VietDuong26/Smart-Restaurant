package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.AreaRequest;
import com.example.SmartRestaurant.dto.response.AreaResponse;
import com.example.SmartRestaurant.entity.AreaEntity;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
    public AreaEntity toEntity(AreaRequest request) {
        return AreaEntity.builder()
                .name(request.getName().trim().toLowerCase())
                .description(request.getDescription())
                .build();
    }

    public AreaResponse toResponse(AreaEntity category) {
        return AreaResponse.builder()
                .id(category.getId())
                .name(category.getName().toUpperCase())
                .description(category.getDescription())
                .status(category.getStatus())
                .build();
    }
}
