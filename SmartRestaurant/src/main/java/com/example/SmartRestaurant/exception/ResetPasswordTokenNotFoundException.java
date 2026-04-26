package com.example.SmartRestaurant.exception;

public class ResetPasswordTokenNotFoundException extends AppException {
    public ResetPasswordTokenNotFoundException() {
        super("Reset password token không tồn tại");
    }
}
