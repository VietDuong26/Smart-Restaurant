package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.enums.IngredientType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IngredientResponse {
    private Long id;
    private String name;
    private IngredientType type;
    private String unit;
    private Double minStock;
    private Double yieldRate;
}
