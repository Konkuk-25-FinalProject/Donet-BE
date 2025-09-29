package com.donet.donet.auth.adapter.out.cache;

import com.donet.donet.auth.application.port.out.LogoutedTokenManagerPort;
import com.donet.donet.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogoutedTokenManager implements LogoutedTokenManagerPort {
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    /**
     * 로그아웃된 토큰으로 등록 (만료까지 차단)
     * */
    public void add(String accessToken) {
        String tokenKey = getLogoutedTokenKey(accessToken);
        Duration ttl = getTtl(accessToken);

        log.info("tokenKey = {} ttl = {}", tokenKey, ttl);
        // 토큰 자체를 키로 보관 + TTL
        redisTemplate.opsForValue().set(tokenKey, "1", ttl);
    }

    private String getLogoutedTokenKey(String accessToken) {
        return "logout:token:" + jwtUtil.resolveJti(accessToken);
    }

    private Duration getTtl(String accessToken) {
        Instant exp = jwtUtil.resolveExpireTime(accessToken);
        Duration ttl = Duration.between(Instant.now(), exp);
        return ttl;
    }

    /**
     * 로그아웃 여부 반환
     *  */
    @Override
    public boolean isLogouted(String accessToken) {
        String tokenKey = getLogoutedTokenKey(accessToken);
        log.info("tokenKey = {}", tokenKey);
        Boolean exists = redisTemplate.hasKey(tokenKey);
        return Boolean.TRUE.equals(exists);
    }
}
