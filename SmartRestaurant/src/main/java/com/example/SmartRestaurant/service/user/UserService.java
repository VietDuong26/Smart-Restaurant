package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.service.base.IBaseService;

public interface UserService extends IBaseService<UserRequest, UserResponse, Long> {
    void activateAccount(Long userId, String otp);

    void resendOTP(Long userId);
}