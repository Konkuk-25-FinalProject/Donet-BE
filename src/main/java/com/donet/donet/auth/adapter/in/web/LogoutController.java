package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.application.port.in.LogoutUsecase;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.LOGOUT;

@RequiredArgsConstructor
@RestController
public class LogoutController implements AuthController{
    private final LogoutUsecase logoutUsecase;

    @Operation(
            summary = "로그아웃",
            description = "로그아웃하여서 엑세스 토큰과 리프레시 토큰을 더이상 사용할 수 없게 만들 수 있다."
    )
    @CustomExceptionDescription(LOGOUT)
    @PostMapping("/auth/logout")
    public BaseResponse<Void> logout(@Parameter(hidden = true) @CurrentUserId Long userId, HttpServletRequest request){
        String accessToken = (String) request.getAttribute("access_token");
        logoutUsecase.logout(userId, accessToken);
        return new BaseResponse<>(null);
    }
}
