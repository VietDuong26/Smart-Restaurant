package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItemRequest {
    @NotNull(message = "Ingredient ID is required")
    private Long ingredientId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be >= 0")
    private Float quantity;
}