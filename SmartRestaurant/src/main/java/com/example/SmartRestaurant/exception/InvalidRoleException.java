package com.example.SmartRestaurant.exception;

public class InvalidRoleException extends AppException {
    public InvalidRoleException() {
        super("Vai trò không hợp lệ");
    }
}
