package com.donet.donet.auth.application.port.in;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
