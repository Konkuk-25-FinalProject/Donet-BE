package com.donet.donet.user.adapter.out.persistence;

import com.donet.donet.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public User mapToDomainEntity(UserJpaEntity jpaEntity) {
        return new User(jpaEntity.getId(),
                jpaEntity.getNickname(),
                jpaEntity.getProfileImage(),
                jpaEntity.getLoginProvider(),
                jpaEntity.getLoginId());
    }

    public UserJpaEntity mapToJpaEntity(User entity){
        return new UserJpaEntity(null,
                entity.getNickname(),
                entity.getProfileImage(),
                entity.getLoginProvider(),
                entity.getLoginId());
    }
}
