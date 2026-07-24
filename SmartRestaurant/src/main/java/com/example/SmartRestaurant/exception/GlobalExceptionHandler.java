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
        return ResponseEntity.status(500).body(new ApiResponse<>(
                500
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

    @ExceptionHandler(InvalidAccountStatusException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidAccountStatusException(InvalidAccountStatusException e) {
        return ResponseEntity.status(409).body(new ApiResponse<>(
                409
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(new ApiResponse<>(
                404
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

    @ExceptionHandler(OTPRateLimitException.class)
    public ResponseEntity<ApiResponse<?>> handleOTPRateLimitException(OTPRateLimitException e) {
        return ResponseEntity.status(429).body(new ApiResponse<>(
                429
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

    @ExceptionHandler(OTPResendLimitExceededException.class)
    public ResponseEntity<ApiResponse<?>> handleOTPResendLimitExceededException(OTPResendLimitExceededException e) {
        return ResponseEntity.status(429).body(new ApiResponse<>(
                429
                , e.getMessage()
                , null
                , LocalDateTime.now()
        ));
    }

}
