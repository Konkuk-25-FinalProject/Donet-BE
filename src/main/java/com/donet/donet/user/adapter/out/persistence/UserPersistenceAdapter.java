package com.donet.donet.user.adapter.out.persistence;

import com.donet.donet.user.application.out.CreateUserPort;
import com.donet.donet.user.application.port.out.FindUserPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserPersistenceAdapter implements FindUserPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(userJpaEntity -> {
                    return Optional.of(userEntityMapper.mapToDomainEntity(userJpaEntity));
                })
                .orElseGet(() -> Optional.empty());
    }
}
