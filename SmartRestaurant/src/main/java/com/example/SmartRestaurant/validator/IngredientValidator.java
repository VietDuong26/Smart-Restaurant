package com.example.SmartRestaurant.validator;

import com.example.SmartRestaurant.dto.request.IngredientRequest;
import com.example.SmartRestaurant.exception.ValidateException;

public final class IngredientValidator {
    public static void validateIngredientRequest(IngredientRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ValidateException("Tên nguyên liệu không được để trống");
        }

    }
}
