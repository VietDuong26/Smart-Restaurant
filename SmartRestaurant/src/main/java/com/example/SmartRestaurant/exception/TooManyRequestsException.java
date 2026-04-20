package com.example.SmartRestaurant.exception;

public class TooManyRequestsException extends AppException {
    public TooManyRequestsException() {
        super("Hành động không khả dụng. Vui lòng thử lại sau");
    }
}
