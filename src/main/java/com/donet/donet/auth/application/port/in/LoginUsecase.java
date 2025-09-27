package com.donet.donet.auth.application.port.in;

import com.donet.donet.auth.application.port.in.dto.LoginCommand;
import com.donet.donet.auth.application.port.in.dto.LoginResponse;

public interface LoginUsecase {
    LoginResponse login(LoginCommand loginCommand);
}
