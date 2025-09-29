package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.application.port.in.LogoutUsecase;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LogoutController {
    private final LogoutUsecase logoutUsecase;

    @PostMapping("/auth/logout")
    public BaseResponse<Void> logout(@CurrentUserId Long userId, HttpServletRequest request){
        String accessToken = (String) request.getAttribute("access_token");
        logoutUsecase.logout(userId, accessToken);
        return new BaseResponse<>(null);
    }
}
