package com.example.SmartRestaurant.validator;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.exception.ValidateException;

public final class ShopValidator {
    public static void validateCreate(ShopRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ValidateException("Tên shop");
        }
    }
}
