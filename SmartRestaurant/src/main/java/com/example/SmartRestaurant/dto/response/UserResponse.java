package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.enums.AccountType;
import com.example.SmartRestaurant.common.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String email;
    private String phoneNumber;
    private String name;
    private String avatarUrl;
    private UserStatus status;
    private AccountType type;
    private LocalDateTime createdAt;
    private List<RoleResponse> roleResponses;
}
