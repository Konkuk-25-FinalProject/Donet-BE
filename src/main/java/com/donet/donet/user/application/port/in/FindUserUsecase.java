package com.donet.donet.user.application.port.in;

import com.donet.donet.user.domain.User;

import java.util.Optional;

public interface FindUserUsecase {
    Optional<User> findByLoginId(String loginId);
}
