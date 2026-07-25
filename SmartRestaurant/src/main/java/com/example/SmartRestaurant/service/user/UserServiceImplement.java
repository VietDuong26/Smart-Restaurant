package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.enums.UserStatus;
import com.example.SmartRestaurant.config.jwt.JwtService;
import com.example.SmartRestaurant.config.userdetails.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ActivateRequest;
import com.example.SmartRestaurant.dto.request.LoginRequest;
import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.InvalidAccountStatusException;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.OTPResendLimitExceededException;
import com.example.SmartRestaurant.exception.UnauthorizedException;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.service.otp.OTPService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.example.SmartRestaurant.validator.AuthValidator.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(noRollbackFor = OTPResendLimitExceededException.class)
public class UserServiceImplement implements UserService {
    UserRepository repository;
    UserMapper mapper;

    OTPService otpService;

    PasswordEncoder passwordEncoder;

    JwtService jwtService;

    @Override
    public void register(RegisterRequest registerRequest) {
        validateRegister(registerRequest);
        UserEntity user = mapper.toEntity(registerRequest);
        user.setStatus(UserStatus.PENDING);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
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

    @Override
    public void activateAccount(ActivateRequest activateRequest) {
        validateActivateAccount(activateRequest);
        UserEntity user = repository.findByEmail(activateRequest.getEmail());
        if (user == null) {
            throw new NotFoundException("Người dùng");
        }
        if (!user.getStatus().equals(UserStatus.PENDING)) {
            throw new InvalidAccountStatusException();
        }
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
        otpService.activate(user.getId(), activateRequest.getCode());
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateLogin(loginRequest);
        UserEntity user = repository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new NotFoundException("Người dùng");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException();
        }
        switch (user.getStatus()) {
            case PENDING, LOCKED, DELETED -> throw new InvalidAccountStatusException();
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleEntity role : user.getRoles()) {
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.getName())
            );


            for (PermissionEntity permission : role.getPermissions()) {
                authorities.add(
                        new SimpleGrantedAuthority(permission.getName())
                );
            }
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateToken(userDetails);
//        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
//        refreshToken.setValue(UUID.randomUUID().toString());
//        refreshToken.setUser(user);
//        refreshToken.setExpiredAt(LocalDateTime.now().plusDays(refreshExpiration));
//        refreshTokenRepository.save(refreshToken);
        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
