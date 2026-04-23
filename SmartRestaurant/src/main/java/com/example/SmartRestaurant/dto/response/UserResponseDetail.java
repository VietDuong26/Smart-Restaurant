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
public class UserResponseDetail {
    private Long id;
    private String name;
    private String phoneNumber;
    private String password;
    private UserStatus status;
    private List<String> roles;
    private List<String> permissions;
    private String email;
}
