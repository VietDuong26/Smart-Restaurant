package com.example.SmartRestaurant.exception;

public class OTPCreateException extends AppException {
    public OTPCreateException(String message) {
        super("Đã có lỗi xảy ra khi tạo OTP " + message);
    }
}
