package com.donet.donet.auth.application.port.in;

public interface LogoutUsecase {
    void logout(Long userId, String accessToken);
}
