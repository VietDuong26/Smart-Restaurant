package com.example.SmartRestaurant.service.otp;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.entity.OTPEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.*;
import com.example.SmartRestaurant.repository.OTPRepository;
import com.example.SmartRestaurant.repository.UserRepository;
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
    UserRepository userRepository;
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
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        switch (user.getStatus()) {
            case ACTIVE:
                throw new OTPAlreadyUsedException();
            case BLOCKED:
                throw new AccountBlockedException();
            case DELETED:
                throw new AccountDeletedException();
        }
        OTPEntity otp = repository.findByUser_EmailAndStatus(email, OTPStatus.PENDING);
        LocalDateTime lastTime = otp.getUpdatedAt() != null
                ? otp.getUpdatedAt()
                : otp.getCreatedAt();
        //rate limit
        if (lastTime.isAfter(LocalDateTime.now().minusSeconds(60)) && otp.getAttempt() > 5) {
            throw new TooManyRequestsException();
        }
        String newOtp = generateOTP();
        LocalDateTime now = LocalDateTime.now();
        otp.setUpdatedAt(now);
        otp.setExpiredAt(now.plusMinutes(5));
        otp.setOtpToken(newOtp);
        repository.save(otp);
        emailService.sendOtp(user.getEmail(), user.getName(), otp.getOtpToken());
    }


}
