package com.example.SmartRestaurant.exception;

public class ProductNotFoundException extends AppException {
    public ProductNotFoundException() {
        super("Không tìm thấy sản phẩm");
    }
}
