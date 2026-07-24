package com.example.SmartRestaurant.exception;

public class CategoryNotFoundException extends AppException {
    public CategoryNotFoundException() {
        super("Không tìm thấy danh mục");
    }
}
