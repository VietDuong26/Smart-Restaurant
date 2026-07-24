package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.dto.request.MailRequest;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.repository.OTPRepository;
import com.example.SmartRestaurant.service.email.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OTPServiceImplement implements OTPService {
    OTPRepository repository;
    EmailService emailService;

    @Override
    public void save(OTPEntity otpEntity) {
        otpEntity.setCode(OTPgenerator());
        otpEntity.setResendCount(0);
        repository.save(otpEntity);
        emailService.sendOtpEmail(new MailRequest(
                otpEntity.getUser().getEmail()
                , otpEntity.getCode()
                , otpEntity.getUser().getName())
        );
    }

    public String OTPgenerator() {
        SecureRandom SECURE_RANDOM = new SecureRandom();
        StringBuilder otp = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            otp.append(SECURE_RANDOM.nextInt(10));
        }
        return otp.toString();
    }
}
