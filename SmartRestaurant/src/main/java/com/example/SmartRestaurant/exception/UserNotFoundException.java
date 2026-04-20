package com.example.SmartRestaurant.exception;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String email) {
        super("Không tìm thấy người dùng với email: " + email);
    }
}
