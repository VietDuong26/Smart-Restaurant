package com.example.SmartRestaurant.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SupplierResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private int status;
    private Long shopId;
    private String shopName;
}