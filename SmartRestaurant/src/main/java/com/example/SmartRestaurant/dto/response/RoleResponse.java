package com.example.SmartRestaurant.dto.response;

import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private List<String> permissions;
}