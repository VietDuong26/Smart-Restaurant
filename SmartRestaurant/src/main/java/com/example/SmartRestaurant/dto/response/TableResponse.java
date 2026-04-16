package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TableResponse {
    private Long id;
    private String name;
    private String qrCode;
    private String status;
    private Long shopId;
    private String shopName;
}