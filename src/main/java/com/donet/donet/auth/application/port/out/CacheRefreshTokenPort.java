package com.donet.donet.auth.application.port.out;

public interface CacheRefreshTokenPort {
    void update(Long userId, String refreshToken);

    String find(Long userId);
}
