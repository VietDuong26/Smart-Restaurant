package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.enums.ShopStatus;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShopResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private LocalTime openTime;
    private LocalTime closeTime;
    private ShopStatus status;
    private String statusReason;
}
