package com.example.SmartRestaurant.dto.response;

import lombok.*;
import java.time.LocalTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ShopResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Long userId;
    private String ownerName;
    private LocalTime openTime;
    private LocalTime closeTime;
}