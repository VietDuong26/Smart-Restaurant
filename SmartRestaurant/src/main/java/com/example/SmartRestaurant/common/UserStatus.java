package com.example.SmartRestaurant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum UserStatus {
    PENDING(0),
    ACTIVE(1),
    INACTIVE(2),
    BLOCKED(999);
    private static final Map<Integer, UserStatus> MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(UserStatus::getValue, e -> e));
    private int value;

    public static UserStatus fromValue(int value) {
        UserStatus status = MAP.get(value);
        if (status == null) {
            throw new IllegalArgumentException("Invalid UserStatus value: " + value);
        }
        return status;
    }
}
