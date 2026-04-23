package com.example.SmartRestaurant.exception;

public class AccountInactivatedException extends AppException {
    public AccountInactivatedException() {
        super("Tài khoản chưa được kích hoạt");
    }
}
