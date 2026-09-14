package com.dmolano.mobile.utils;

import java.util.UUID;

public class RandomDataGenerator {

    private static final String BASE_EMAIL = "d.molano@globant.com";
    private static final String PASSWORD = "Elalfajor?";

    public static String randomEmail() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        // Usa plus-addressing: mismo buzón base, pero único en cada corrida
        String[] parts = BASE_EMAIL.split("@");
        return parts[0] + "+" + uniqueSuffix + "@" + parts[1];
    }

    public static String password() {
        return PASSWORD;
    }
}