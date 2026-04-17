package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.DuplicateDataException;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.OTPRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.util.otp.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    OTPRepository otpRepository;
    @Autowired
    UserMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OTPService otpService;

    @Override
    public UserResponse create(UserRequest userRequest) {
        if (repository.findByPhoneNumber(userRequest.getPhoneNumber()) == null) {
            UserEntity user = mapper.toEntity(userRequest);
            user.setStatus(UserStatus.PENDING.getValue());
            user = repository.save(user);
            OTPEntity otp = new OTPEntity();
            otp.setOtpToken(otpService.generateOTP());
            otp.setUser(user);
            otp.setCreatedAt(LocalDateTime.now());
            otp.setStatus(0);
            otpRepository.save(otp);
            return mapper.toResponse(user);
        } else {
            throw new DuplicateDataException("Số điện thoại");
        }

    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse getById(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> getAll() {
        return null;
    }

    @Override
    public void activateAccount(Long userId, String otp) {
        
    }
}
