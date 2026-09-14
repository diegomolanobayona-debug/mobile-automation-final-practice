package com.dmolano.mobile.utils;

import java.util.UUID;

/**
 * Generates test data for the Sign Up / Login scenarios. Keeping this data
 * generation out of the Page Object layer (as advised in class) lets each
 * test control its own credentials without hardcoding them in screens.
 */
public class RandomDataGenerator {

    private static final String BASE_EMAIL = "d.molano@globant.com";
    private static final String PASSWORD = "Elalfajor?";

    /**
     * Builds a unique email on every call using plus-addressing on a fixed
     * base mailbox, so Sign Up can be executed repeatedly without
     * colliding on an already-registered address.
     *
     * @return a unique, valid-format email address
     */
    public static String randomEmail() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String[] parts = BASE_EMAIL.split("@");
        return parts[0] + "+" + uniqueSuffix + "@" + parts[1];
    }

    /** @return the fixed password used for test accounts (meets the app's 8-character minimum) */
    public static String password() {
        return PASSWORD;
    }
}