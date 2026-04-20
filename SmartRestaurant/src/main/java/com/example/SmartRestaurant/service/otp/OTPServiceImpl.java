package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.exception.*;
import com.example.SmartRestaurant.repository.OTPRepository;
import com.example.SmartRestaurant.service.user.UserService;
import com.example.SmartRestaurant.util.mail.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OTPServiceImpl implements OTPService {
    OTPRepository repository;
    UserService userService;
    EmailService emailService;

    @Override
    public String generateOTP() {
        int otp = 100000 + new SecureRandom().nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public OTPEntity findByUserEmailAndStatus(String email, OTPStatus status) {
        return repository.findByUser_EmailAndStatus(email, status);
    }


    @Override
    public void create(OTPEntity otpEntity) {
        try {
            repository.save(otpEntity);
        } catch (Exception e) {
            System.out.println("OTP bug:" + e.getMessage());
        }
    }

    @Override
    public OTPEntity validateOTPToken(String email, String OTPValue) {
        OTPEntity otp = repository.findByUser_EmailAndStatus(email, OTPStatus.PENDING);
        if (otp == null || !otp.getOtpToken().equals(OTPValue)) {
            throw new InvalidOTPException();
        }

        if (otp.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredOTPException();
        }

        return otp;
    }

    @Override
    public void resendOTP(String email) {

        OTPEntity otp = repository.findByUser_EmailAndStatus(email, OTPStatus.PENDING);
        UserResponse user = userService.getByEmail(email);
        if (otp != null && otp.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(60))) {
            throw new TooManyRequestsException(); // chưa đủ 60s
        }
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        switch (user.getStatus()) {
            case ACTIVE:
                throw new OTPAlreadyUsedException();
            case BLOCKED:
                throw new AccountBlockedException();
            case DELETED:
                throw new AccountDeletedException();
        }

        String newOtp = generateOTP();
        otp.setUpdatedAt(LocalDateTime.now());
        otp.setOtpToken(newOtp);
        repository.save(otp);
        emailService.sendOtp(user.getEmail(), user.getName(), otp.getOtpToken());
    }


}
