package com.example.SmartRestaurant.util.otp;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPService {
    public String generateOTP() {
        int otp = 100000 + new SecureRandom().nextInt(900000);
        return String.valueOf(otp);
    }
}
