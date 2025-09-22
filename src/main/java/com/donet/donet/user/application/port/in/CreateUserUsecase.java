package com.donet.donet.user.application.port.in;

import com.donet.donet.user.domain.User;

public interface CreateUserUsecase {
    User save(User newUser);
}
