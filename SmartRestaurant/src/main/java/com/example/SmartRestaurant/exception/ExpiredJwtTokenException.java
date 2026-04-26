package com.example.SmartRestaurant.exception;

public class ExpiredJwtTokenException extends AppException {
    public ExpiredJwtTokenException() {
        super("jwt token hết hạn");
    }
}
