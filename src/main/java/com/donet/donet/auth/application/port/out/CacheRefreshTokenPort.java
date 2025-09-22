package com.donet.donet.auth.application.port.out;

import java.util.Optional;

public interface CacheRefreshTokenPort {
    void update(Long userId, String refreshToken);

    Optional<String> findById(Long userId);

    void save(Long userId, String refreshToken);
}
