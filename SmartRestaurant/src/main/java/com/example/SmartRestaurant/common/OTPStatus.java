package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OTPStatus {
    PENDING(0),
    USED(1);

    private int value;
}
