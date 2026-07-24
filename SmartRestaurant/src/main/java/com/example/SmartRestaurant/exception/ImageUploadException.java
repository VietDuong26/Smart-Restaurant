package com.example.SmartRestaurant.exception;

public class ImageUploadException extends AppException {

    public ImageUploadException(String cause) {
        super("Upload ảnh bị lỗi " + cause);
    }
}
