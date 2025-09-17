package com.donet.donet.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())  // JWT 사용하니 csrf 공격 대비 필요 없음
                .formLogin(login -> login.disable()) // JWT 사용하니 form login 필요 없음
                .httpBasic(httpBasic-> httpBasic.disable()) // http Basic 방식 사용안함
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // JWT 사용하니 세션을 사용하지 않음

        // 엔드포인트별 인증 인가 설정
        http
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/auth/**").permitAll());

        return http.build();
    }
}
