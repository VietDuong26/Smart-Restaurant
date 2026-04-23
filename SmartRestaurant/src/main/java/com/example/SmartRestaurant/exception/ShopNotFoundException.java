package com.example.SmartRestaurant.exception;

public class ShopNotFoundException extends AppException {
    public ShopNotFoundException() {
        super("Không tìm thấy cửa hàng");
    }
}
