package com.donet.donet.user.application;

import com.donet.donet.user.application.port.in.CreateUserUsecase;
import com.donet.donet.user.application.port.out.CreateUserPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUsecase {
    private final CreateUserPort createUserPort;
    @Override
    public User save(User newUser) {
        return createUserPort.save(newUser);
    }
}
