package com.example.SmartRestaurant.dto.response;

import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private int status;
    private List<String> roles;
}