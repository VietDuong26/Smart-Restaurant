package com.example.SmartRestaurant.dto.request;

import com.example.SmartRestaurant.common.enums.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientRequest {
    private String name;
    private IngredientType type;
    private String unit;
    private Double minStock;
    private Double yieldRate;
}
