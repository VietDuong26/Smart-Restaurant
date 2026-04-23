package com.example.SmartRestaurant.exception;

public class ShopBlockedException extends AppException {
    public ShopBlockedException() {
        super("Cửa hàng của bạn đã vi phạm tiêu chuẩn. Vui lòng liên hệ hỗ trợ");
    }
}
