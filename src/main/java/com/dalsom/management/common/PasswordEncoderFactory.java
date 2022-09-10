package com.dalsom.management.common;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderFactory {

    private static final Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();
    public static final String DEFAULT_ENCODER_NAME = "default";

    private PasswordEncoderFactory() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void addEncoder(String name, PasswordEncoder encoder) {
        ENCODER_MAP.put(name, encoder);
    }

    public static PasswordEncoder getDefaultEncoder() {
        return getEncoder(DEFAULT_ENCODER_NAME);
    }

    public static PasswordEncoder getEncoder(String name) {
        return ENCODER_MAP.get(name);
    }
}
