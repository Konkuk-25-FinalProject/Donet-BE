package com.donet.donet.auth.application.port.in.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
