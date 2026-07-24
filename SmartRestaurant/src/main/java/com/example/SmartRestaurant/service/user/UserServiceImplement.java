package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.enums.UserStatus;
import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.InvalidAccountStatusException;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.OTPResendLimitExceededException;
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
import static com.example.SmartRestaurant.validator.AuthValidator.validateResendOTP;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(noRollbackFor = OTPResendLimitExceededException.class)
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

    @Override
    public void resendOTP(String email) {
        validateResendOTP(email);
        UserEntity user = repository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("Người dùng");
        }
        if (!user.getStatus().equals(UserStatus.PENDING)) {
            throw new InvalidAccountStatusException();
        }
        otpService.resendOTP(user.getId());
    }
}
