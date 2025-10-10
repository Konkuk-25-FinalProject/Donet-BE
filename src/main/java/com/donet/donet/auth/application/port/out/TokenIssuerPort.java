package com.donet.donet.auth.application.port.out;

public interface TokenIssuerPort {
    String createAccessToken(Long userId);

    String createRefreshToken(Long userId);

    boolean validate(String token);

    Long resolveUserId(String token);
}
