package com.example.SmartRestaurant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TableRequest {
    private String name;
    private String qrCode;
    private Long shopId;
}
