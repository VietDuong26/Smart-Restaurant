package com.example.SmartRestaurant.exception;

public class OTPAlreadyUsedException extends AppException {
    public OTPAlreadyUsedException() {
        super("OTP đã được sử dụng");
    }
}
