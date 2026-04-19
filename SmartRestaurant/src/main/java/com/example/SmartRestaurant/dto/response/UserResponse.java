package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private UserStatus status;
    private List<String> roles;
    private String email;
}