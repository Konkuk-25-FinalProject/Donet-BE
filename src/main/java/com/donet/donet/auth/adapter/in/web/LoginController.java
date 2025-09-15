package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.application.port.in.LoginCommand;
import com.donet.donet.auth.application.port.in.LoginResponse;
import com.donet.donet.auth.application.port.in.LoginUsecase;
import com.donet.donet.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginUsecase loginUsecase;

    @PostMapping("/auth/login/kakao")
    public BaseResponse<LoginResponse> kakaoLogin(String code){
        return new BaseResponse<>(loginUsecase.login(new LoginCommand(code)));
    }
}
