package com.example.SmartRestaurant.exception;

public class ExpiredJwtTokenException extends AppException {
    public ExpiredJwtTokenException() {
        super("JWT token đã hết hạn");
    }
}
