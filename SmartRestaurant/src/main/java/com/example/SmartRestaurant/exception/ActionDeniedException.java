package com.example.SmartRestaurant.exception;

public class ActionDeniedException extends AppException {
    public ActionDeniedException() {
        super("Hành động không khả dụng");
    }
}
