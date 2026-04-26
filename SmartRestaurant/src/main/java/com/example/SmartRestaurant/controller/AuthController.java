package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.*;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/roles")
    @Operation(summary = "Thêm roles cho user")
    ResponseEntity<ApiResponse<UserResponse>> addRoles(
            @Validated @RequestBody List<Long> roleIds
            , @PathVariable("id") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(201
                        , "Success"
                        , userService.addRoles(userId, roleIds)
                        , LocalDateTime.now()
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/permissions")
    @Operation(summary = "Thêm quyền cho user")
    ResponseEntity<ApiResponse<UserResponse>> addPermissions(
            @Validated @RequestBody List<Long> permissionIds
            , @PathVariable("id") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(201
                        , "Success"
                        , userService.addPermissions(userId, permissionIds)
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/register")
    @Operation(summary = "Đăng ký tài khoản")
    ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(201
                        , "Success"
                        , userService.create(userRequest)
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/activate-account")
    @Operation(summary = "Kích hoạt tài khoản qua OTP")
    ResponseEntity<ApiResponse<UserResponse>> activateAccount(@Valid @RequestBody OTPRequest otpRequest) {
        userService.activateAccount(otpRequest.getEmail(), otpRequest.getOtpToken());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(204
                        , "Success"
                        , null
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/resend-otp")
    @Operation(summary = "Gửi lại OTP")
    ResponseEntity<ApiResponse<UserResponse>> resendOTP(@Valid @RequestParam String email) {
        userService.resendOTP(email);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(200
                        , "Success"
                        , null
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/login")
    @Operation(summary = "Đăng nhập")
    ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request
            , HttpServletResponse response) {
        LoginResponse loginResponse = userService.login(request);
        Cookie cookie = new Cookie("refreshToken_user" + loginResponse.getUserResponse().getId(), loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200
                , "Success"
                , loginResponse
                , LocalDateTime.now()
        ));

    }

    @PostMapping("/refresh")
    @Operation(summary = "Làm mới access token")
    ResponseEntity<ApiResponse<?>> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200
                , "Success"
                , userService.refreshTokenHandle(refreshToken)
                , LocalDateTime.now()
        ));

    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','MANAGER','CUSTOMER')")
    @Operation(summary = "Xem thông tin user đang đăng nhập")
    @GetMapping("/me")
    ResponseEntity<ApiResponse<UserResponse>> getMe(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200
                , "Success"
                , userService.getById(userDetails.getUser().getId())
                , LocalDateTime.now()));
    }

    @PostMapping("/logout")
    @Operation(summary = "Đăng xuất")
    ResponseEntity<ApiResponse<?>> logout(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userService.logout(userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(204
                , "Logout thành công"
                , null
                , LocalDateTime.now()));
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Quên mật khẩu — gửi email reset")
    ResponseEntity<ApiResponse<?>> forgotPassword(@RequestParam String email) {
        userService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(204
                , "Success"
                , null
                , LocalDateTime.now()
        ));
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Đặt lại mật khẩu")
    ResponseEntity<ApiResponse<?>> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(204
                , "Success"
                , null
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','MANAGER','CUSTOMER')")
    @PostMapping("/change-password")
    @Operation(summary = "Đổi mật khẩu")
    ResponseEntity<ApiResponse<?>> changePassword(@Valid @RequestBody ChangePasswordRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        request.setUser(userDetails.getUser());
        userService.changePassword(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(204
                , "Success"
                , null
                , LocalDateTime.now()
        ));
    }
}
