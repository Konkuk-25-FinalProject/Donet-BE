package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.application.port.in.dto.LoginCommand;
import com.donet.donet.auth.application.port.in.dto.LoginResponse;
import com.donet.donet.auth.application.port.in.LoginUsecase;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.*;

@RequiredArgsConstructor
@RestController
public class LoginController implements AuthController{
    private final LoginUsecase loginUsecase;

    @Operation(
            summary = "카카오 로그인",
            description = "카카오 인증 서버로부터 받은 인가코드를 보내면 Donet 전용 토큰을 받을 수 있다"
    )
    @CustomExceptionDescription(DEFAULT)
    @PostMapping("/auth/login/kakao")
    public BaseResponse<LoginResponse> kakaoLogin(@Parameter(name = "카카오 인가코드") @RequestParam("code") @NotBlank String code){
        return new BaseResponse<>(loginUsecase.login(new LoginCommand(code)));
    }
}
