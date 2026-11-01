package com.example.SmartRestaurant.dto.request;

import com.example.SmartRestaurant.common.enums.PrintRuleType;
import com.example.SmartRestaurant.entity.HostDeviceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintJobRequest {
    private PrintRuleType type;
    private String contentData;
    private HostDeviceEntity hostDevice;
}
