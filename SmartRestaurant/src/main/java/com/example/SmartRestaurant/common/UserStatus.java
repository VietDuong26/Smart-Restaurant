package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    PENDING,
    ACTIVE,
    BLOCKED,
    DELETED
}
