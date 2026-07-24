package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.enums.UserStatus;
import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.service.otp.OTPService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.SmartRestaurant.validator.AuthValidator.validateRegister;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class UserServiceImplement implements UserService {
    UserRepository repository;
    UserMapper mapper;

    OTPService otpService;

    @Override
    public void register(RegisterRequest registerRequest) {
        validateRegister(registerRequest);
        UserEntity user = mapper.toEntity(registerRequest);
        user.setStatus(UserStatus.PENDING);
        UserEntity savedUser = repository.save(user);
        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setUser(savedUser);
        otpEntity.setExpiredAt(LocalDateTime.now().plusMinutes(5));
        otpService.save(otpEntity);
    }
}
