package com.example.SmartRestaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private UserResponse user;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime createdAt;
    private int totalTables;
}
