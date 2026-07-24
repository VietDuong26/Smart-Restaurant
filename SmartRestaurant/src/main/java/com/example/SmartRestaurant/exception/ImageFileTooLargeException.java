package com.example.SmartRestaurant.exception;

public class ImageFileTooLargeException extends AppException {
    public ImageFileTooLargeException() {
        super("Dung lượng file quá lớn");
    }
}
