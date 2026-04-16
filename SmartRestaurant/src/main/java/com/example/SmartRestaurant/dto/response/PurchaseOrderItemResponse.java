package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseOrderItemResponse {
    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private int ingredientUnit;
    private Float quantity;
}