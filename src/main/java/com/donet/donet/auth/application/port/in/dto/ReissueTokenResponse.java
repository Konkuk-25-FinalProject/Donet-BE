package com.donet.donet.auth.application.port.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토큰 재발급 응답")
public record ReissueTokenResponse(
        @Schema(description = "엑세스 토큰")
        String accessToken,
        @Schema(description = "리프레시 토큰")
        String refreshToken
) {
}
