package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class IngredientRequest {
    @NotBlank(message = "Ingredient name is required")
    private String name;

    private int unit;

    @Min(value = 0, message = "Current stock must be >= 0")
    private Float currentStock;

    private Float minStock;
    private int type;
    private Float yieldRate;

    @NotNull(message = "Shop ID is required")
    private Long shopId;
}