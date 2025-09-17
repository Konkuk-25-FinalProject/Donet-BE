package com.donet.donet.global.security;

import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.jwt.exception.InvalidJwtException;
import com.donet.donet.global.jwt.exception.JwtExpiredException;
import com.donet.donet.global.jwt.exception.JwtNotFoundException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.donet.donet.global.security.JwtErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if(token != null){
            try{
                Claims claims = jwtUtil.validateToken(token);

                // userDetails 조회
                String email = claims.get("email" ,String.class);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // 인증토큰을 생성하고 시큐리티 컨텍스트홀더에 저장
                Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            // entryPoint에 전달할 에러 코드들
            catch(InvalidJwtException e){
                log.info("[doFilterInternal] 올바르지 않은 JWT입니다", e);
                request.setAttribute("jwt_error_code", INVALID_JWT_ERROR);
            }
            catch(JwtExpiredException e){
                log.info("[doFilterInternal] 만료된 JWT입니다", e);
                request.setAttribute("jwt_error_code", EXPIRED_JWT_ERROR);
            }
            catch(JwtNotFoundException e){
                log.info("[doFilterInternal] JWT가 비어 있습니다", e);
                request.setAttribute("jwt_error_code", JWT_NOT_FOUND_ERROR);
            }
            catch(UsernameNotFoundException e){
                log.info("[doFilterInternal] JWT subject/email과 일치하는 유저가 없습니다", e);
                request.setAttribute("jwt_error_code", INVALID_JWT_ERROR);
            }
        }

        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.split(" ")[1];
            return token;
        }
        log.info("토큰이 존재하지 않습니다!");
        return null;
    }
}