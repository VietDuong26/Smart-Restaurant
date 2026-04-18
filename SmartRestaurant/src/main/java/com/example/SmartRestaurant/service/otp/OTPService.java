package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.entity.OTPEntity;

public interface OTPService {
    String generateOTP();

    boolean validateOTP(Long userId, String OTPvalue);

    void create(OTPEntity otpEntity);
}
