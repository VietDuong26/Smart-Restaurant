package com.example.SmartRestaurant.exception;

public class ExpiredOTPException extends AppException {
    public ExpiredOTPException() {
        super("OTP đã hết hạn");
    }
}
