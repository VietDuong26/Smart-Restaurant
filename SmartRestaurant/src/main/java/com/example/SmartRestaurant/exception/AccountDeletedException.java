package com.example.SmartRestaurant.exception;

public class AccountDeletedException extends AppException {
    public AccountDeletedException() {
        super("Tài khoản đã bị vô hiệu hóa. Liên hệ để khôi phục.");
    }
}
