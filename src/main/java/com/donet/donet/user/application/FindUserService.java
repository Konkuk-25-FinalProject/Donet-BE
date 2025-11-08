package com.donet.donet.user.application;

import com.donet.donet.user.application.port.in.FindUserUsecase;
import com.donet.donet.user.application.port.out.FindUserPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserService implements FindUserUsecase {
    private final FindUserPort findUserPort;
    @Override
    public Optional<User> findByLoginId(String loginId) {
        return findUserPort.findByLoginId(loginId);
    }
}
