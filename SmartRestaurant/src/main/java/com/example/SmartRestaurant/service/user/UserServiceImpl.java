package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.config.jwt.JwtService;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ChangePasswordRequest;
import com.example.SmartRestaurant.dto.request.LoginRequest;
import com.example.SmartRestaurant.dto.request.ResetPasswordRequest;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.*;
import com.example.SmartRestaurant.exception.*;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.*;
import com.example.SmartRestaurant.service.otp.OTPService;
import com.example.SmartRestaurant.util.mail.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class UserServiceImpl implements UserService {

    final UserRepository repository;
    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;
    final RefreshTokenRepository refreshTokenRepository;
    final UserMapper mapper;
    final PasswordEncoder passwordEncoder;
    final OTPService otpService;
    final EmailService emailService;
    final JwtService jwtService;
    @Value("${refresh-token.expiration}")
    Long refreshExpiration;

    ResetPasswordTokenRepository resetPasswordTokenRepository;

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
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        if (user.getStatus() == UserStatus.DELETED) {
            throw new UserNotFoundException();
        }

        return mapper.toResponse(user);
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
            throw new UserNotFoundException();
        }
        if (otp == null) {
            throw new InvalidOTPException();
        }

        otp.setStatus(OTPStatus.USED);
        otp.setAttempt(1);
        user.setStatus(UserStatus.ACTIVE);

        otpService.create(otp);
        repository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity user = repository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException();
        }
        switch (user.getStatus()) {
            case PENDING -> throw new AccountInactivatedException();
            case BLOCKED -> throw new AccountBlockedException();
            case DELETED -> throw new AccountDeletedException();
        }
        Collection<SimpleGrantedAuthority> authorities = Stream.concat(
                user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())),
                user.getPermissions().stream().map(x -> new SimpleGrantedAuthority(x.getName()))).toList();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateToken(userDetails);
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setValue(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiredAt(LocalDateTime.now().plusDays(refreshExpiration));
        refreshTokenRepository.save(refreshToken);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getValue())
                .userResponse(mapper.toResponse(user))
                .build();
    }


    @Override
    public void resendOTP(String email) {
        otpService.resendOTP(email);
    }

    @Override
    public UserResponse addRoles(Long userId, List<Long> roleIds) {
        UserEntity user = repository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Set<RoleEntity> roles = user.getRoles();
        for (Long roleId : roleIds) {
            RoleEntity role = roleRepository.getById(roleId);
            if (role == null) {
                throw new InvalidRoleException();
            }
            if (!roles.contains(role)) {
                roles.add(role);
            }
        }
        user.setRoles(roles);
        return mapper.toResponse(repository.save(user));
    }

    @Override
    public UserResponse addPermissions(Long userId, List<Long> permissionIds) {
        UserEntity user = repository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Set<PermissionEntity> permissions = user.getPermissions();
        for (Long permissionId : permissionIds) {
            PermissionEntity permission = permissionRepository.getById(permissionId);
            if (permission == null) {
                throw new InvalidPermissionException();
            }
            if (!permissions.contains(permission)) {
                permissions.add(permission);
            }
        }
        user.setPermissions(permissions);
        return mapper.toResponse(user);
    }

    @Override
    public UserEntity getByPhoneNumber(String phoneNumber) {
        UserEntity user = repository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public LoginResponse refreshTokenHandle(String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByValueAndDeletedAtIsNull(refreshToken);
        if (refreshTokenEntity == null) {
            throw new RefreshTokenNotFoundException();
        }
        LocalDateTime now = LocalDateTime.now();
        if (refreshTokenEntity.getExpiredAt().isBefore(now)
                || (refreshTokenEntity.getDeletedAt() != null && refreshTokenEntity.getDeletedAt().isBefore(now))) {
            throw new ExpiredRefreshTokenException();
        }
        return LoginResponse.builder()
                .refreshToken(refreshTokenEntity.getValue())
                .accessToken(jwtService.generateToken(new CustomUserDetails(refreshTokenEntity.getUser())))
                .userResponse(mapper.toResponse(refreshTokenEntity.getUser()))
                .build();
    }

    @Override
    public void logout(Long userId) {
        UserEntity user = repository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        LocalDateTime now = LocalDateTime.now();
        List<RefreshTokenEntity> refreshTokens = refreshTokenRepository.findAllByUser_IdAndDeletedAtIsNull(user.getId());
        refreshTokens.forEach(token -> token.setDeletedAt(now));
        refreshTokenRepository.saveAll(refreshTokens);
        SecurityContextHolder.clearContext();
    }

    @Override
    public void forgotPassword(String email) {
        UserEntity user = repository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        ResetPasswordTokenEntity resetPasswordToken = new ResetPasswordTokenEntity();
        resetPasswordToken.setValue(UUID.randomUUID().toString());
        resetPasswordToken.setUser(user);
        resetPasswordToken.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        resetPasswordTokenRepository.save(resetPasswordToken);

        emailService.sendResetPassword(user.getEmail(), user.getName(), resetPasswordToken.getValue());
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        ResetPasswordTokenEntity resetPasswordTokenEntity = resetPasswordTokenRepository.findByValueAndAndDeletedAtIsNull(resetPasswordRequest.getResetToken());
        if (resetPasswordTokenEntity == null) {
            throw new ResetPasswordTokenNotFoundException();
        }
        resetPasswordTokenEntity.setDeletedAt(LocalDateTime.now());
        resetPasswordTokenRepository.save(resetPasswordTokenEntity);

        UserEntity user = resetPasswordTokenEntity.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        repository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        UserEntity user = request.getUser();
        if (!passwordEncoder.matches(user.getPassword(), request.getOldPassword())) {
            throw new BadCredentialsException();
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }
}
