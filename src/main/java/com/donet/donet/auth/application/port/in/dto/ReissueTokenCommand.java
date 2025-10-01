package com.donet.donet.auth.application.port.in.dto;

public record ReissueTokenCommand(
        String refreshToken
) {
}
