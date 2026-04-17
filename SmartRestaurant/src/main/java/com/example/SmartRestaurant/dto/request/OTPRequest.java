package com.example.SmartRestaurant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPRequest {
    private Long userId;
    private String otpToken;
}
