package com.example.SmartRestaurant.service.user;

import com.example.SmartRestaurant.dto.request.RegisterRequest;

public interface UserService {
    void register(RegisterRequest registerRequest);

    void resendOTP(String email);
}
