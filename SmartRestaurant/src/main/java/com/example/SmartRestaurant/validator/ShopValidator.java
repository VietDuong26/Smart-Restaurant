package com.example.SmartRestaurant.validator;

import com.example.SmartRestaurant.dto.request.ReasonRequest;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.exception.ValidateException;

public final class ShopValidator {
    public static void validateShopRequest(ShopRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ValidateException("Tên shop không được để trống");
        }
    }

    public static void validateReject(ReasonRequest reason) {
        if (reason.getReason() == null || reason.getReason().isBlank()) {
            throw new ValidateException("Lý do từ chối không được để trống");
        }
    }

    public static void validateLock(ReasonRequest reason) {
        if (reason.getReason() == null || reason.getReason().isBlank()) {
            throw new ValidateException("Lý do khóa không được để trống");
        }
    }
}
