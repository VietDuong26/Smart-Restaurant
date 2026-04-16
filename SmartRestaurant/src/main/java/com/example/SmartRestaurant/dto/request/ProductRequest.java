package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be >= 0")
    private Float price;

    private String description;
    private String imageUrl;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private int status;
}