package com.example.SmartRestaurant.exception;

public class ShopInactivatedException extends AppException {
    public ShopInactivatedException() {
        super("Cửa hàng chưa được kích hoạt");
    }
}
