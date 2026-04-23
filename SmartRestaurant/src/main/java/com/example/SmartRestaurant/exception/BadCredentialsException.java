package com.example.SmartRestaurant.exception;

public class BadCredentialsException extends AppException {
    public BadCredentialsException() {
        super("Sai tài khoản hoặc mật khẩu");
    }
}
