package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Float price;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;
    private int status;
}