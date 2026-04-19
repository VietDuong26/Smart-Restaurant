package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.entity.OTPEntity;

public interface OTPService {
    String generateOTP();

    OTPEntity findByUserEmailAndStatus(String user, int status);

    void create(OTPEntity otpEntity);

}
