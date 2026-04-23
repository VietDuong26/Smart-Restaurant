package com.example.SmartRestaurant.exception;

public class ShopDeletedException extends AppException {
    public ShopDeletedException() {
        super("Cửa hàng đã bị vô hiệu hóa. Liên hệ để khôi phục.");
    }
}
