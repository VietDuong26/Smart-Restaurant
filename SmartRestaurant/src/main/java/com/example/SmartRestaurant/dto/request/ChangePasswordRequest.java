package com.example.SmartRestaurant.dto.request;

import com.example.SmartRestaurant.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String oldPassword;
    @NotBlank(message = "Mật khẩu mới không được bỏ trống")
    private String newPassword;
    private UserEntity user;
}
