package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseOrderRequest {
    @NotNull(message = "Shop ID is required")
    private Long shopId;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotEmpty(message = "Purchase order must have at least one item")
    private List<PurchaseOrderItemRequest> items;
}