package com.utils;

import java.util.UUID;

public class RandomUserEmailCreation {

    public static String generateRandomEmail() {
        return "user" + UUID.randomUUID() + "@example.com";
    }
}
