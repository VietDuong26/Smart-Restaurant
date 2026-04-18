package com.example.SmartRestaurant.util.mail;

import com.example.SmartRestaurant.common.Const;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    JavaMailSender mailSender;

    @Async("taskExecutor")
    public void sendOtp(String to, String username, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("OTP Verification");
            helper.setText(Const.HTML_OTP_MAIL.formatted(username, otp), true);
            mailSender.send(message);
            System.out.println("Thread: " + Thread.currentThread().getName());
        } catch (MessagingException e) {
            System.out.println("Failed to send OTP email to {}" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error when sending OTP to {}" + e.getMessage());
        }
    }
}
