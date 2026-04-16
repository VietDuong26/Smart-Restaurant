package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PermissionRequest {
    @NotBlank(message = "Permission name is required")
    private String name;
}