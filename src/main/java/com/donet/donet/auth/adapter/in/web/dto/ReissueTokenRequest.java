package com.donet.donet.auth.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토큰 재발급 요청")
public record ReissueTokenRequest(
        @Schema(description = "리프레시 토큰")
        String refreshToken
) {

}
