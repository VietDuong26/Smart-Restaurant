package com.example.SmartRestaurant.exception;

public class IvalidOTPException extends AppException {
    public IvalidOTPException() {
        super("Token không hợp lệ");
    }
}
