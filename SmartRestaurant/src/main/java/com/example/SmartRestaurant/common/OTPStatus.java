package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OTPStatus {
    VALID(0),

    INVALID(1);

    private int value;
}
