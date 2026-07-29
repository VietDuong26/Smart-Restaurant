package com.example.SmartRestaurant.validator;

import com.example.SmartRestaurant.dto.request.IngredientRequest;
import com.example.SmartRestaurant.exception.ValidateException;

public final class IngredientValidator {
    public static void validateIngredientRequest(IngredientRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ValidateException("Tên nguyên liệu không được để trống");
        }
        if (request.getType() == null) {
            throw new ValidateException("Loại nguyên liệu không được để trống");
        }

        if (request.getUnit() == null || request.getUnit().isBlank()) {
            throw new ValidateException("Đơn vị nguyên liệu không được để trống");
        }

        if (request.getUnit().trim().length() > 20) {
            throw new ValidateException("Đơn vị không được vượt quá 20 ký tự");
        }

        if (request.getMinStock() == null) {
            throw new ValidateException("Tồn kho tối thiểu không được để trống");
        }

        if (request.getMinStock() < 0) {
            throw new ValidateException("Tồn kho tối thiểu không được nhỏ hơn 0");
        }

        if (request.getYieldRate() == null) {
            throw new ValidateException("Tỷ lệ thu hồi không được để trống");
        }

        if (request.getYieldRate() <= 0
                || request.getYieldRate() > 1) {
            throw new ValidateException("Tỷ lệ thu hồi phải lớn hơn 0 và nhỏ hơn hoặc bằng 1");
        }
    }
}
