package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserRequest request) {
        if (request == null) return null;

        return UserEntity.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    public UserResponse toResponse(UserEntity user) {
        if (user == null) {
            throw new UserNotFoundException();
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .email(user.getEmail())
                .build();
    }

    
}