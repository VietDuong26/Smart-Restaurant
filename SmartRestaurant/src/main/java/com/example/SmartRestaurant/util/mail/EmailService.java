package com.example.SmartRestaurant.util.mail;

import com.example.SmartRestaurant.common.Const;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    JavaMailSender mailSender;

    public void sendOtp(String to, String username, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("OTP Verification");
        helper.setText(Const.HTML_OTP_MAIL.formatted(username, otp), true);
        mailSender.send(message);
    }
}
