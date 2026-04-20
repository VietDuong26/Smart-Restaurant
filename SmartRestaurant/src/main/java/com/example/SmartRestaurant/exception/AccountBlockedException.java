package com.example.SmartRestaurant.exception;

public class AccountBlockedException extends AppException {
    public AccountBlockedException() {
        super("Tài khoản của bạn đã vi phạm tiêu chuẩn. Vui lòng liên hệ hỗ trợ");
    }
}
