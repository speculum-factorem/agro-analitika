package com.agroanalytics.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterOutcome {
    private long expiresInSeconds;
    private boolean emailVerificationRequired;
    /** Заполнено, если подтверждение email отключено — сразу выдаём JWT. */
    private LoginResponse session;
}
