package com.example.SmartRestaurant.exception;

public class InvalidOTPException extends AppException {
    public InvalidOTPException() {
        super("Token không hợp lệ");
    }
}
