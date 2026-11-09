package com.example.SmartRestaurant.dto.response;

import com.example.SmartRestaurant.common.enums.PrintRuleType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PrintJobResponse {
    private Long id;
    private PrintRuleType type;
    private String contentData;
}
