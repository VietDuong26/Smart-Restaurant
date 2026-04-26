package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Token không được để trống")
    private String resetToken;
    @NotBlank(message = "Mật khẩu mới không được bỏ trống")
    private String newPassword;
}
