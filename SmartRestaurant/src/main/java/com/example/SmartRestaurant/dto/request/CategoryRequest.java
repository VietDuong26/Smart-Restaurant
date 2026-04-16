package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

    @NotNull(message = "Shop ID is required")
    private Long shopId;
}