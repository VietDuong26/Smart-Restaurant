package com.example.SmartRestaurant.exception;

import com.example.SmartRestaurant.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ApiResponse<?>> handleValidateException(ValidateException e) {
        return ResponseEntity.status(400).body(new ApiResponse<>(
                400
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<ApiResponse<?>> handleEmailSendException(EmailSendException e) {
        return ResponseEntity.status(400).body(new ApiResponse<>(
                500
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }
}
