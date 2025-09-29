package com.donet.donet.user.application.port.out;

import com.donet.donet.user.domain.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByLoginId(String loginId);

    Optional<User> findById(Long userId);

    User save(User newUser);
}
