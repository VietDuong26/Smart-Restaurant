package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.DuplicateDataException;
import com.example.SmartRestaurant.exception.InvalidOTPException;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.service.otp.OTPService;
import com.example.SmartRestaurant.util.mail.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository repository;

    UserMapper mapper;

    PasswordEncoder passwordEncoder;

    OTPService otpService;

    EmailService emailService;


    @Override
    public UserResponse create(UserRequest userRequest) {
        if (repository.findByPhoneNumber(userRequest.getPhoneNumber()) == null) {
            UserEntity user = mapper.toEntity(userRequest);
            user.setStatus(UserStatus.PENDING);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user = repository.save(user);

            OTPEntity otp = new OTPEntity();
            LocalDateTime otpTime = LocalDateTime.now();
            otp.setOtpToken(otpService.generateOTP());
            otp.setUser(user);
            otp.setCreatedAt(otpTime);
            otp.setExpiredAt(otpTime.plusMinutes(5));
            otp.setStatus(OTPStatus.PENDING);
            otpService.create(otp);
            emailService.sendOtp(user.getEmail(), user.getName(), otp.getOtpToken());
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
    public void activateAccount(String email, String OTPValue) {
        UserEntity user = repository.findByEmail(email);
        OTPEntity otp = otpService.validateOTPToken(email, OTPValue);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        if (otp == null) {
            throw new InvalidOTPException();
        }

        otp.setStatus(OTPStatus.USED);
        user.setStatus(UserStatus.ACTIVE);

        otpService.create(otp);
        repository.save(user);
    }

    @Override
    public UserResponse getByEmail(String email) {
        return mapper.toResponse(repository.findByEmail(email));
    }

    @Override
    public void resendOTP(String email) {
        UserEntity user = repository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        otpService.resendOTP(email);
    }


}
