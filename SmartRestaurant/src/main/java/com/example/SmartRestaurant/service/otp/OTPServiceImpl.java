package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.repository.OTPRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OTPServiceImpl implements OTPService {
    OTPRepository repository;

    @Override
    public String generateOTP() {
        int otp = 100000 + new SecureRandom().nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public OTPEntity findByUserEmailAndStatus(String email, int status) {
        try {
            return repository.findByUserEmailAndStatus(email, status);
        } catch (Exception e) {
            System.out.println("OTP not found by email:" + email);
            return null;
        }
    }


    @Override
    public void create(OTPEntity otpEntity) {
        try {
            repository.save(otpEntity);
        } catch (Exception e) {
            System.out.println("OTP bug:" + e.getMessage());
        }
    }

}
