package com.donet.donet.auth.application;

import com.donet.donet.auth.application.port.in.LogoutUsecase;
import com.donet.donet.auth.application.port.out.CacheRefreshTokenPort;
import com.donet.donet.auth.application.port.out.LogoutedTokenManagerPort;
import com.donet.donet.global.exception.CustomException;
import com.donet.donet.user.application.port.out.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogoutService implements LogoutUsecase {
    private final UserRepositoryPort userRepositoryPort;
    private final LogoutedTokenManagerPort tokenBlackListManagerPort;
    private final CacheRefreshTokenPort cacheRefreshTokenPort;

    @Transactional
    @Override
    public void logout(Long userId, String accessToken) {
        log.info("[logout] userId = {} token={}", userId, accessToken);
        // 유저를 조회
        userRepositoryPort.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 엑세스 토큰을 블랙리스트에 추가
        tokenBlackListManagerPort.add(accessToken);

        // 리프레시 토큰을 날린다
        cacheRefreshTokenPort.delete(userId);
    }
}
