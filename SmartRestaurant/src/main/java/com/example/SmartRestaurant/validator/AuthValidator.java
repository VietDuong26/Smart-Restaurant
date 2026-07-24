package com.example.SmartRestaurant.validator;

import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.exception.ValidateException;

public final class AuthValidator {
    public static void validateRegister(RegisterRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ValidateException("Tên không được bỏ trống");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new ValidateException("Email không được bỏ trống");
        }

        if (!request.getEmail().trim().endsWith("@gmail.com")) {
            throw new ValidateException("Email không hợp lệ");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new ValidateException("Mật khẩu không được bỏ trống");
        }
        if (request.getPassword().length() < 8) {
            throw new ValidateException("Mật khẩu phải có từ 8 kí tự trở lên");
        }
    }

    public static void validateResendOTP(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidateException("Email không được bỏ trống");
        }

        if (!email.trim().endsWith("@gmail.com")) {
            throw new ValidateException("Email không hợp lệ");
        }
    }
}
