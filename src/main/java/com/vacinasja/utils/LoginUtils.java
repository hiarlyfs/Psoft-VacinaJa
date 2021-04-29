package com.vacinasja.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class LoginUtils {
    private LoginUtils() {}

    public static String generatePassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}
