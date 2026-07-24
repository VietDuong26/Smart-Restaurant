package com.example.SmartRestaurant.util.requestutil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class PhoneNumberUtil {
    public static String normalizePhoneNumber(String phoneNumber) {
        String normalized = phoneNumber.trim();
        if (normalized.startsWith("+84")) {
            return "0" + normalized.substring(3);
        }
        return normalized;
    }
}
