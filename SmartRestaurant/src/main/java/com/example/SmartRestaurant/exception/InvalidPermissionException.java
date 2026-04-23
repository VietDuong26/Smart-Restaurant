package com.example.SmartRestaurant.exception;

public class InvalidPermissionException extends AppException {
    public InvalidPermissionException() {
        super("Quyền không hợp lệ");
    }
}
