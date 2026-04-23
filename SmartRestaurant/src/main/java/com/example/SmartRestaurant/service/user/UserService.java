package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.dto.request.LoginRequest;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.dto.response.UserResponseDetail;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface UserService extends IBaseService<UserRequest, UserResponse, Long> {
    void activateAccount(String email, String otp);

    LoginResponse login(LoginRequest request);

    void resendOTP(String email);

    UserResponse addRoles(Long userId, List<Long> roleIds);

    UserResponse addPermissions(Long userId, List<Long> permissionIds);

    UserResponseDetail getByPhoneNumber(String phoneNumber);
}