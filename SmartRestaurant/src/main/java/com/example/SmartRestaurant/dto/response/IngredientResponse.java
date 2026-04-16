package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class IngredientResponse {
    private Long id;
    private String name;
    private int unit;
    private Float currentStock;
    private Float minStock;
    private int type;
    private Float yieldRate;
    private Long shopId;
    private String shopName;
}