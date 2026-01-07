package com.rescreen.rescreen_service.security.jwt;

public final class JwtConstants {

    private JwtConstants() {}

    public static final String SECRET_KEY =
            "RESCREEN_SERVICE_SECRET_KEY_256_BITS_LONG_SECURE_123456";

    public static final long EXPIRATION_TIME =
            1000 * 60 * 60; // 1 hour
}
