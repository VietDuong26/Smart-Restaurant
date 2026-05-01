package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TableStatus {
    AVAILABLE,
    RESERVED,
    OCCUPIED,
    OUT_OF_SERVICE,
    CLEANING
}
