package com.example.SmartRestaurant.exception;

public class CategoryNotFoundInShopException extends AppException {
    public CategoryNotFoundInShopException() {
        super("Danh mục không tồn tại trong shop");
    }
}
