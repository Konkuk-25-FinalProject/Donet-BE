package com.donet.donet.auth.adapter.out.oauth;

public record KakaoOAuthTokenResponse(
        String access_token,
        String token_type,
        String refresh_token,
        int expires_in,
        String scope,
        int refresh_token_expires_in
) {
}
