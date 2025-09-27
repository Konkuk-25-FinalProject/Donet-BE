package com.donet.donet.auth.application.port.in.dto;

public record ReissueTokenResponse(
        String accessToken,
        String refreshToken
) {
}
