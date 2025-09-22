package com.donet.donet.auth.application;

import com.donet.donet.auth.application.port.in.LoginCommand;
import com.donet.donet.auth.application.port.in.LoginResponse;
import com.donet.donet.auth.application.port.in.LoginUsecase;
import com.donet.donet.auth.application.port.out.KakaoAuthPort;
import com.donet.donet.auth.application.port.out.KakaoOAuthToken;
import com.donet.donet.auth.application.port.out.KakaoUserProfile;
import com.donet.donet.auth.application.port.out.TokenIssuerPort;
import com.donet.donet.user.application.port.in.CreateUserUsecase;
import com.donet.donet.user.application.port.in.FindUserUsecase;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements LoginUsecase {
    private final KakaoAuthPort kakaoAuthPort;
    private final FindUserUsecase findUserUsecase;
    private final CreateUserUsecase createUserUsecase;
    private final TokenIssuerPort tokenIssuerPort;

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        String code = loginCommand.code();
        KakaoOAuthToken oAuthToken = kakaoAuthPort.requestToken(code);
        KakaoUserProfile userProfile = kakaoAuthPort.requestUserProfile(oAuthToken);

        // 신규 회원이면 가입시킨다
        User user = findUserUsecase.findByLoginId(userProfile.id())
                .orElseGet(()-> registerUser(userProfile));

        log.info("[login] userId = {}인 사용자가 조회됨", user.getId());

        String accessToken = tokenIssuerPort.createAccessToken(user.getId());
        String refreshToken = tokenIssuerPort.createRefreshToken(user.getId());
        return new LoginResponse(accessToken, refreshToken);
    }

    private User registerUser(KakaoUserProfile userProfile){
        log.info("[registerUser] 첫 로그인 사용자를 새로운 유저로 등록");
        User newUser = new User(null,
                userProfile.properties().nickname(),
                userProfile.kakao_account().profile().thumbnail_image_url(),
                "KAKAO",
                userProfile.id());
        return createUserUsecase.save(newUser);
    }
}
