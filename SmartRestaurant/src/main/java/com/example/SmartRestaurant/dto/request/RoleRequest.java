package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoleRequest {
    @NotBlank(message = "Role name is required")
    private String name;

    private String description;
    private List<Long> permissionIds;
}