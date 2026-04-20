package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserEntity toEntity(UserRequest request) {
        if (request == null) return null;

        return UserEntity.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .status(request.getStatus())
                .email(request.getEmail())
                .build();
    }

    public UserResponse toResponse(UserEntity user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .email(user.getEmail())
                .roles(
                        user.getRoles() == null ? null :
                                user.getRoles().stream()
                                        .map(role -> role.getName())
                                        .collect(Collectors.toList())
                )
                .build();
    }
}