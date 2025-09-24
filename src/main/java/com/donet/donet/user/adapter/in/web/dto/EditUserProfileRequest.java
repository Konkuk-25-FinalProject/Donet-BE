package com.donet.donet.user.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record EditUserProfileRequest(
    @NotBlank String nickname,
    @NotBlank String walletAddress
) {
}
