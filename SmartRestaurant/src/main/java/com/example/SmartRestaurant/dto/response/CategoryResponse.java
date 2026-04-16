package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Long shopId;
    private String shopName;
}