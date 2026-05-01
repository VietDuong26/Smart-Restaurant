package com.example.SmartRestaurant.exception;

public class TableNotFoundInShopException extends AppException {
    public TableNotFoundInShopException(Long tableId, Long shopId) {
        super("Bàn không tồn tại trong shop");
    }
}
