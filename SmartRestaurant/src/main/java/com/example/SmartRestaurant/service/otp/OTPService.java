package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.entity.OTPEntity;

public interface OTPService {
    String generateOTP();

    OTPEntity findByUserEmailAndStatus(String email, OTPStatus status);

    void create(OTPEntity otpEntity);

    OTPEntity validateOTPToken(String email, String OTPValue);

    void resendOTP(String email);

}
