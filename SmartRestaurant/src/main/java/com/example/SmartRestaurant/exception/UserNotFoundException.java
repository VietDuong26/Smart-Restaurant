package com.example.SmartRestaurant.exception;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
        super("Không tìm thấy người dùng");
    }
}
