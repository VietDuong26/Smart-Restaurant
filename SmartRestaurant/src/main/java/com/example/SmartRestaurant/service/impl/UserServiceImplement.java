package com.example.SmartRestaurant.service.impl;

import com.example.SmartRestaurant.common.enums.UserStatus;
import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.example.SmartRestaurant.validator.AuthValidator.validateRegister;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImplement implements UserService {
    UserRepository repository;
    UserMapper mapper;

    @Override
    public void register(RegisterRequest registerRequest) {
        validateRegister(registerRequest);
        UserEntity user = mapper.toEntity(registerRequest);
        user.setStatus(UserStatus.PENDING);
        repository.save(user);
    }
}
