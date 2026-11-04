package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserMapper {
    RoleMapper roleMapper;

    public UserEntity toEntity(RegisterRequest registerRequest) {
        return UserEntity.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }

    public UserResponse toResponse(UserEntity user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .avatarUrl(user.getAvatarUrl())
                .status(user.getStatus())
                .type(user.getType())
                .createdAt(user.getCreatedAt())
                .roleResponses(user.getRoles().stream().map(x -> roleMapper.toResponse(x)).toList())
                .build();
    }
}
