package com.example.SmartRestaurant.exception;

public class RefreshTokenNotFoundException extends AppException {
    public RefreshTokenNotFoundException() {
        super("Refresh token không tồn tại");
    }
}
