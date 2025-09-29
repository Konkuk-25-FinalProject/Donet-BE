package com.donet.donet.user.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "프로필 수정 요청")
public record EditUserProfileRequest(
    @Schema(description = "변경하거나 유지할 닉네임")
    @NotBlank String nickname,

    @Schema(description = "변경하거나 유지할 지갑주소")
    @NotBlank String walletAddress
) {
}
