package com.donet.donet.user.application.port.out;

import com.donet.donet.user.domain.User;

import java.util.Optional;

public interface FindUserPort {
    Optional<User> findByLoginId(String loginId);

    Optional<User> findById(Long userId);
}
