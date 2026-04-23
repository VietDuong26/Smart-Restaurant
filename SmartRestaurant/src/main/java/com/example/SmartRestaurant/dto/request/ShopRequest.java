package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopRequest {

    @NotBlank(message = "Tên shop không được trống")
    private String name;

    @NotBlank(message = "Địa chỉ không được trống")
    private String address;

    @NotBlank(message = "Số điện thoại không được trống")
    private String phoneNumber;

    @NotBlank(message = "Người dùng không được để trống")
    private Long userId;

    @NotNull(message = "Giờ mở cửa không được trống")
    private LocalTime openTime;

    @NotNull(message = "Giờ đóng cửa không được trống")
    private LocalTime closeTime;
}
