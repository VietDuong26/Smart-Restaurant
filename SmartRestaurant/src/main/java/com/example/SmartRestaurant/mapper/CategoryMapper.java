package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.CategoryRequest;
import com.example.SmartRestaurant.dto.response.CategoryResponse;
import com.example.SmartRestaurant.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static CategoryEntity toEntity(CategoryRequest request) {
        if (request == null) return null;

        return CategoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static CategoryResponse toResponse(CategoryEntity category) {
        if (category == null) return null;

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}