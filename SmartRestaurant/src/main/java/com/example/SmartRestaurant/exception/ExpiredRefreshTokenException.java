package com.example.SmartRestaurant.exception;

public class ExpiredRefreshTokenException extends AppException {
    public ExpiredRefreshTokenException() {
        super("Refresh token đã hết hạn, vui lòng đăng nhập lại");
    }
}
