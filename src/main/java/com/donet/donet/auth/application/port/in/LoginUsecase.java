package com.donet.donet.auth.application.port.in;

public interface LoginUsecase {
    LoginResponse login(LoginCommand loginCommand);
}
