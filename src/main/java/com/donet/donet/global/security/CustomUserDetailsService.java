package com.donet.donet.global.security;

import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJpaEntity user = userRepository.findByLoginId(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(username + "와 일치하는 사용자가 없습니다");
        });
        return new CustomUserDetails(user);
    }
}
