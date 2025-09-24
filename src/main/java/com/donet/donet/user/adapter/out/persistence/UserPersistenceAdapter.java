package com.donet.donet.user.adapter.out.persistence;

import com.donet.donet.user.application.port.out.UserRepositoryPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserPersistenceAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(userJpaEntity -> Optional.of(userEntityMapper.mapToDomainEntity(userJpaEntity)))
                .orElseGet(() -> Optional.empty());
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId)
                .map(jpaEntity -> Optional.of(userEntityMapper.mapToDomainEntity(jpaEntity)))
                .orElseGet(() -> Optional.empty());
    }

    @Override
    public User save(User user) {
        UserJpaEntity jpaEntity = userEntityMapper.mapToJpaEntity(user);
        UserJpaEntity save = userRepository.save(jpaEntity);
        return userEntityMapper.mapToDomainEntity(save);
    }
}
