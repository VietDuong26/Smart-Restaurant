package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopStatus {
    PENDING,
    ACTIVE,
    BLOCKED,
    DELETED
}
