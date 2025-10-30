package com.donet.donet.global.util;

import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataFactory {
    @Autowired
    UserRepository userRepository;

    public UserJpaEntity createUser() {
        return userRepository.save(new UserJpaEntity(null, "닉네임", "이미지",  "KAKAO", "kakaocode", "waller"));
    }
}
