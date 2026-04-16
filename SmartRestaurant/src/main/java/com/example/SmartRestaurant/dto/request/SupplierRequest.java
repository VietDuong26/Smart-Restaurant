package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SupplierRequest {
    @NotBlank(message = "Supplier name is required")
    private String name;

    private String phone;
    private String address;
    private int status;

    @NotNull(message = "Shop ID is required")
    private Long shopId;
}