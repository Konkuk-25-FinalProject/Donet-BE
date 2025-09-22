package com.donet.donet.auth.application.port.out;

public interface KakaoAuthPort {
    KakaoOAuthToken requestToken(String code);

    KakaoUserProfile requestUserProfile(KakaoOAuthToken oAuthToken);
}
