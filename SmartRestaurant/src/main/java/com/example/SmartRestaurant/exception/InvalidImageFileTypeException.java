package com.example.SmartRestaurant.exception;

public class InvalidImageFileTypeException extends AppException {
    public InvalidImageFileTypeException() {
        super("Chỉ chấp nhận file ảnh");
    }
}
