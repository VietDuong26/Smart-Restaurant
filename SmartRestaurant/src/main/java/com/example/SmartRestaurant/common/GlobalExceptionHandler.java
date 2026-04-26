package com.example.SmartRestaurant.common;

import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.exception.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(404, e.getMessage(), null, LocalDateTime.now()));
    }


    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleShopNotFound(ShopNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(404, e.getMessage(), null, LocalDateTime.now()));
    }


    @ExceptionHandler(AccountBlockedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccountBlocked(AccountBlockedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(403, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(AccountDeletedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccountDeleted(AccountDeletedException e) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(new ApiResponse<>(410, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(AccountInactivatedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccountInactivated(AccountInactivatedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(403, e.getMessage(), null, LocalDateTime.now()));
    }


    @ExceptionHandler(ExpiredOTPException.class)
    public ResponseEntity<ApiResponse<Object>> handleExpiredOTP(ExpiredOTPException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(400, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidOTP(InvalidOTPException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(400, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(OTPAlreadyUsedException.class)
    public ResponseEntity<ApiResponse<Object>> handleOTPAlreadyUsed(OTPAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(409, e.getMessage(), null, LocalDateTime.now()));
    }


    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateData(DuplicateDataException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(409, e.getMessage(), null, LocalDateTime.now()));
    }


    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ApiResponse<Object>> handleTooManyRequests(TooManyRequestsException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ApiResponse<>(429, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, "Internal error", null, LocalDateTime.now()));
    }

    @ExceptionHandler(ShopBlockedException.class)
    public ResponseEntity<ApiResponse<Object>> handleShopBlocked(ShopBlockedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(403, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(ShopDeletedException.class)
    public ResponseEntity<ApiResponse<Object>> handleShopDeleted(ShopDeletedException e) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(new ApiResponse<>(410, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(ShopInactivatedException.class)
    public ResponseEntity<ApiResponse<Object>> handleShopInactivated(ShopInactivatedException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(400, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(ExpiredJwtTokenException.class)
    public ResponseEntity<ApiResponse<Object>> handleExpiredJwtToken(ExpiredJwtTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleRefreshTokenNotFound(RefreshTokenNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(404, e.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<ApiResponse<Object>> handleExpiredRefreshToken(ExpiredRefreshTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, e.getMessage(), null, LocalDateTime.now()));
    }
}
