package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PermissionResponse {
    private Long id;
    private String name;
}