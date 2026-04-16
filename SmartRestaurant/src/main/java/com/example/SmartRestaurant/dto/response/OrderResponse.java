package com.example.SmartRestaurant.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long tableId;
    private String tableName;
    private Long userId;
    private String userName;
    private int status;
    private Float totalAmount;
    private LocalDateTime createdTime;
    private List<OrderItemResponse> items;
}