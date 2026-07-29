package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.enums.ProductStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private ProductStatus status;
    private Double price;
    private String imageUrl;
}
