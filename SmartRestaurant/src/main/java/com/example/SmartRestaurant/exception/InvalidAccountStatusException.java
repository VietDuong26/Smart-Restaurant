package com.example.SmartRestaurant.exception;

public class InvalidAccountStatusException extends AppException {
    public InvalidAccountStatusException() {
        super("Trạng thái tài khoản không được chấp thuận");
    }
}
