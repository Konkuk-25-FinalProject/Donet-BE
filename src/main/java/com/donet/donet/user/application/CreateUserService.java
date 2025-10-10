package com.donet.donet.user.application;

import com.donet.donet.user.application.port.in.CreateUserUsecase;
import com.donet.donet.user.application.port.out.UserRepositoryPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUsecase {
    private final UserRepositoryPort userRepositoryPort;
    @Override
    public User save(User newUser) {
        return userRepositoryPort.save(newUser);
    }
}
