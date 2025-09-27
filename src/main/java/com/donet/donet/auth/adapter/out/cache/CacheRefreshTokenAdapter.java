package com.donet.donet.auth.adapter.out.cache;

import com.donet.donet.auth.application.port.out.CacheRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CacheRefreshTokenAdapter implements CacheRefreshTokenPort {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void update(Long userId, String refreshToken) {
        refreshTokenRepository.findByUserId(userId).ifPresent(refresh ->{
            refresh.changeToken(refreshToken);
            refreshTokenRepository.save(refresh);
        });
    }

    @Override
    public Optional<String> findById(Long userId) {
        return refreshTokenRepository.findByUserId(userId)
                .map(refresh -> Optional.of(refresh.getToken()))
                .orElseGet(() -> Optional.empty());
    }

    @Override
    public void save(Long userId, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));
    }
}
