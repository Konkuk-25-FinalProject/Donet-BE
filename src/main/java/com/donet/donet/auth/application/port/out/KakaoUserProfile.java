package com.donet.donet.auth.application.port.out;

public record KakaoUserProfile(
        String id,
        String nickname,
        String email,
        String thumbnail_image_url
) {
}

