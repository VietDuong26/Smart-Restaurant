package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ShopRequest {
    @NotBlank(message = "Shop name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    private String phoneNumber;

    @NotNull(message = "Owner user ID is required")
    private Long userId;

    private LocalTime openTime;
    private LocalTime closeTime;
}