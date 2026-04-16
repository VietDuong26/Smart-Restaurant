package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TableRequest {
    @NotBlank(message = "Table name is required")
    private String name;

    private String qrCode;
    private String status;

    @NotNull(message = "Shop ID is required")
    private Long shopId;
}