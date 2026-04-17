package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.IngredientRequest;
import com.example.SmartRestaurant.dto.response.IngredientResponse;
import com.example.SmartRestaurant.entity.IngredientEntity;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    public IngredientEntity toEntity(IngredientRequest request) {
        if (request == null) return null;

        return IngredientEntity.builder()
                .name(request.getName())
                .unit(request.getUnit())
                .currentStock(request.getCurrentStock())
                .minStock(request.getMinStock())
                .type(request.getType())
                .yieldRate(request.getYieldRate())
                .build();
    }

    public IngredientResponse toResponse(IngredientEntity ingredient) {
        if (ingredient == null) return null;

        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnit())
                .currentStock(ingredient.getCurrentStock())
                .minStock(ingredient.getMinStock())
                .type(ingredient.getType())
                .yieldRate(ingredient.getYieldRate())
                .build();
    }
}