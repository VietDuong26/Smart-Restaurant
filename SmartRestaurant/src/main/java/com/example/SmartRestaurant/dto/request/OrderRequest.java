package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "Table ID is required")
    private Long tableId;

    private Long userId;
    private int status;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemRequest> items;
}