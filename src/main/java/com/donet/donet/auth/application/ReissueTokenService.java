package com.donet.donet.auth.application;

import com.donet.donet.auth.application.port.in.ReissueTokenUsecase;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenCommand;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenResponse;
import com.donet.donet.auth.application.port.out.CacheRefreshTokenPort;
import com.donet.donet.auth.application.port.out.TokenIssuerPort;
import com.donet.donet.global.exception.CustomException;
import com.donet.donet.user.application.port.out.UserRepositoryPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReissueTokenService implements ReissueTokenUsecase {
    private final CacheRefreshTokenPort cacheRefreshTokenPort;
    private final TokenIssuerPort tokenIssuerPort;
    private final UserRepositoryPort findUserPort;

    @Override
    public ReissueTokenResponse reissue(ReissueTokenCommand command) {
        // 토큰 검증
        if(!tokenIssuerPort.validate(command.refreshToken())){
            throw new CustomException(INVALID_JWT);
        }

        // 유저 조회
        Long userId = tokenIssuerPort.resolveUserId(command.refreshToken());
        User user = findUserPort.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 레디스의 토큰과 일치하는지 확인
        String oldToken = cacheRefreshTokenPort.findById(user.getId())
                .orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));
        if(!oldToken.equals(command.refreshToken())){
            throw new CustomException(REFRESH_TOKEN_NOT_FOUND);
        }

        String accessToken = tokenIssuerPort.createAccessToken(user.getId());
        String refreshToken = tokenIssuerPort.createRefreshToken(user.getId());

        // 새 토큰을 레디스에 저장
        cacheRefreshTokenPort.update(user.getId(), refreshToken);

        return new ReissueTokenResponse(accessToken, refreshToken);
    }
}
