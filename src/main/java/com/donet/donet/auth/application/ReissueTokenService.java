package com.donet.donet.auth.application;

import com.donet.donet.auth.application.port.in.ReissueTokenUsecase;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenCommand;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenResponse;
import com.donet.donet.auth.application.port.out.CacheRefreshTokenPort;
import com.donet.donet.auth.application.port.out.TokenIssuerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReissueTokenService implements ReissueTokenUsecase {
    private final CacheRefreshTokenPort cacheRefreshTokenPort;
    private final TokenIssuerPort tokenIssuerPort;

    @Override
    public ReissueTokenResponse reissue(ReissueTokenCommand command) {
        // 토큰 검증
        if(!tokenIssuerPort.validate(command.refreshToken())){
            throw new RuntimeException();
        }

        // 레디스의 토큰과 일치하는지 확인
       String oldToken = cacheRefreshTokenPort.find(command.userId());
        
        if(!oldToken.equals(command.refreshToken())){
           throw new RuntimeException();
        }
        // 토큰 재발급
        String accessToken = tokenIssuerPort.createAccessToken(command.userId());
        String refreshToken = tokenIssuerPort.createRefreshToken(command.userId());

        // 토큰을 레디스에 저장
        cacheRefreshTokenPort.update(command.userId(), refreshToken);

        // 반환
        return new ReissueTokenResponse(accessToken, refreshToken);
    }
}
