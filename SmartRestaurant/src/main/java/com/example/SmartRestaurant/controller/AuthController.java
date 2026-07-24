package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.dto.request.RegisterRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.example.SmartRestaurant.common.Constant.URL;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(URL + "/auth")
public class AuthController {
    UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Đăng kí tài khoản ")
    ResponseEntity<ApiResponse<?>> register(
            @Valid
            @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(201).body(new ApiResponse<>(
                201
                , "Đăng kí thành công.Vui lòng kiểm tra email của bạn"
                , null
                , LocalDateTime.now()
        ));
    }

    @PostMapping("/resend-otp")
    @Operation(summary = "Gửi lại mã OTP")
    ResponseEntity<ApiResponse<?>> register(
            @Valid
            @RequestBody String email) {
        userService.resendOTP(email);
        return ResponseEntity.status(201).body(new ApiResponse<>(
                201
                , "Gửi lại mã OTP thành công.Vui lòng kiểm tra email của bạn"
                , null
                , LocalDateTime.now()
        ));
    }
}
