package com.example.SmartRestaurant.exception;

public class TableNotFoundException extends AppException {
    public TableNotFoundException() {
        super("Bàn không tồn tại");
    }
}
