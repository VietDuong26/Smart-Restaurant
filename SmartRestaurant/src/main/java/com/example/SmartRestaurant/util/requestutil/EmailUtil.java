package com.example.SmartRestaurant.util.requestutil;

public final class EmailUtil {
    public static String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.strip().toLowerCase();
    }
}
