package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.dto.request.ChangePasswordRequest;
import com.example.SmartRestaurant.dto.request.LoginRequest;
import com.example.SmartRestaurant.dto.request.ResetPasswordRequest;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface UserService extends IBaseService<UserRequest, UserResponse, Long> {
    void activateAccount(String email, String otp);

    LoginResponse login(LoginRequest request);

    void resendOTP(String email);

    UserResponse addRoles(Long userId, List<Long> roleIds);

    UserResponse addPermissions(Long userId, List<Long> permissionIds);

    UserEntity getByPhoneNumber(String phoneNumber);

    LoginResponse refreshTokenHandle(String refreshToken);

    void logout(Long userId);

    void forgotPassword(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void changePassword(ChangePasswordRequest request);
}