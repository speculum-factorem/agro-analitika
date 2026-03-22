package com.agroanalytics.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginOutcome {
    private boolean loginCodeRequired;
    /** Заполнено, если нужен код из письма. */
    private LoginChallengeResponse challenge;
    /** Заполнено, если код входа отключён — сразу JWT. */
    private LoginResponse session;
}
