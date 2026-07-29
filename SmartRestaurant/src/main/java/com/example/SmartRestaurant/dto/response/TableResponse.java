package com.example.SmartRestaurant.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TableResponse {
    private Long id;
    private String name;
    private boolean qrEnabled;
}
