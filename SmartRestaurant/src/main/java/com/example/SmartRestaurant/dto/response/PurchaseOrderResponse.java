package com.example.SmartRestaurant.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PurchaseOrderResponse {
    private Long id;
    private LocalDateTime createdAt;
    private Long shopId;
    private String shopName;
    private Long supplierId;
    private String supplierName;
    private List<PurchaseOrderItemResponse> items;
}