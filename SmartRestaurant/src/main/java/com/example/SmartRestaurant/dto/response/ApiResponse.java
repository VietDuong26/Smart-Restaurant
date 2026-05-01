package com.example.SmartRestaurant.dto.response;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    @Nullable
    private T data;
    private LocalDateTime timestamp;
}
