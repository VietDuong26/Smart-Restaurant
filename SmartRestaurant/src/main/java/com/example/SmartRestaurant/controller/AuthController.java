package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.LoginRequest;
import com.example.SmartRestaurant.dto.request.OTPRequest;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.LoginResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @Operation(
            summary = "Thêm roles cho user",
            description = "API dùng để thêm vai trò cho người dùng đang đăng nhập"
    )
    ResponseEntity<ApiResponse<UserResponse>> addRoles(
            @Valid @RequestBody List<Long> roleIds
            , @PathVariable("id") Long userId) {
        return ResponseEntity.ok(
                new ApiResponse<>(201
                        , "Success"
                        , userService.addRoles(userId, roleIds)
                        , LocalDateTime.now()
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/permisisons")
    @Operation(
            summary = "Thêm quyền cho user",
            description = "API dùng để thêm quyền cho người dùng đang đăng nhập"
    )
    ResponseEntity<ApiResponse<UserResponse>> addPermissions(
            @Valid @RequestBody List<Long> permissionIds
            , @PathVariable("id") Long userId) {
        return ResponseEntity.ok(
                new ApiResponse<>(201
                        , "Success"
                        , userService.addPermissions(userId, permissionIds)
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(
                new ApiResponse<>(201
                        , "Success"
                        , userService.create(userRequest)
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/activate-account")
    ResponseEntity<ApiResponse<UserResponse>> activateAccount(@Valid @RequestBody OTPRequest otpRequest) {
        userService.activateAccount(otpRequest.getEmail(), otpRequest.getOtpToken());
        return ResponseEntity.ok(
                new ApiResponse<>(204
                        , "Success"
                        , null
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/resend-otp")
    ResponseEntity<ApiResponse<UserResponse>> resendOTP(@Valid @RequestParam String email) {
        userService.resendOTP(email);
        return ResponseEntity.ok(
                new ApiResponse<>(200
                        , "Success"
                        , null
                        , LocalDateTime.now()
                )
        );
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(new ApiResponse<>(200
                , "Success"
                , userService.login(request)
                , LocalDateTime.now()
        ));

    }

    @GetMapping("/me")
    ResponseEntity<ApiResponse<UserResponse>> getMe(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(new ApiResponse<>(200
                , "Success"
                , userService.getById(userDetails.getId())
                , LocalDateTime.now()));
    }

//    @PostMapping("/logout")
//    ResponseEntity<ApiResponse<?>> logout() {
//        return ResponseEntity.ok(new ApiResponse(200
//                , "Logout thành công"
//                , null
//                , LocalDateTime.now()));
//    }
//
//    @PostMapping("/forgot-password")
//    ResponseEntity<ApiResponse<UserResponse>> forgotPassword() {
//        try {
//            return ResponseEntity.ok(new ApiResponse<>(200
//                    , "Success"
//                    , null
//                    , LocalDateTime.now()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body((new ApiResponse<>(400
//                    , e.getMessage()
//                    , null
//                    , LocalDateTime.now()
//            )));
//        }
//    }

//    @PostMapping("/reset-password")
//    ResponseEntity<ApiResponse<UserResponse>> resetPassword(@Valid @RequestBody LoginRequest request) {
//        try {
//            userService.login(request);
//            return ResponseEntity.ok(new ApiResponse<>(400
//                    , "Success"
//                    , null
//                    , LocalDateTime.now()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body((new ApiResponse<>(400
//                    , e.getMessage()
//                    , null
//                    , LocalDateTime.now()
//            )));
//        }
//    }
//
//    @PostMapping("/change-password")
//    ResponseEntity<ApiResponse<UserResponse>> changePassword(@Valid @RequestBody LoginRequest request) {
//        try {
//            userService.login(request);
//            return ResponseEntity.ok(new ApiResponse<>(400
//                    , "Success"
//                    , null
//                    , LocalDateTime.now()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body((new ApiResponse<>(400
//                    , e.getMessage()
//                    , null
//                    , LocalDateTime.now()
//            )));
//        }
//    }
}
