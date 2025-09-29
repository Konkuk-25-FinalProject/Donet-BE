package com.donet.donet.global.config;

import com.donet.donet.global.security.CustomAuthenticationEntryPoint;
import com.donet.donet.global.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final String[] PERMIT_URL = {
            "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/swagger-ui/index.html"
    };
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
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/login/**", "/auth/reissue/token").permitAll()
                        .requestMatchers(PERMIT_URL).permitAll()
                        .anyRequest().authenticated());

        // JWT 인증 필터 추가
        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 예외 핸들러 추가
        http
                .exceptionHandling(
                        configurer -> configurer.authenticationEntryPoint(customAuthenticationEntryPoint));

        return http.build();
    }
}
