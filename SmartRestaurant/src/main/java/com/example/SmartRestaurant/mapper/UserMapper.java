package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(RegisterRequest registerRequest) {
        return UserEntity.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }
}
