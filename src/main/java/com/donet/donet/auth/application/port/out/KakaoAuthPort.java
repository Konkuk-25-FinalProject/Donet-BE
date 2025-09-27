package com.donet.donet.auth.application.port.out;

import com.donet.donet.auth.application.port.out.dto.KakaoOAuthToken;
import com.donet.donet.auth.application.port.out.dto.KakaoUserProfile;

public interface KakaoAuthPort {
    KakaoOAuthToken requestToken(String code);

    KakaoUserProfile requestUserProfile(KakaoOAuthToken oAuthToken);
}
