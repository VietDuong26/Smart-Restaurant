package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TableResponse {
    private Long id;
    private String name;
    private String qrCode;
    private TableStatus status;
}
