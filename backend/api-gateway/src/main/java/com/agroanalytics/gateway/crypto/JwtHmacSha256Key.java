package com.agroanalytics.gateway.crypto;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Должен совпадать с auth-service {@code com.agroanalytics.auth.crypto.JwtHmacSha256Key}.
 */
public final class JwtHmacSha256Key {

    private JwtHmacSha256Key() {
    }

    public static SecretKey fromEnvSecret(String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("jwt.secret must not be blank");
        }
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            try {
                keyBytes = MessageDigest.getInstance("SHA-256").digest(keyBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException(e);
            }
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
