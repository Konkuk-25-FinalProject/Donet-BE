package com.donet.donet.global.jwt;

import com.donet.donet.auth.application.port.out.TokenIssuerPort;
import com.donet.donet.global.jwt.exception.InvalidJwtException;
import com.donet.donet.global.jwt.exception.JwtExpiredException;
import com.donet.donet.global.jwt.exception.JwtNotFoundException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.*;

@Component
public class JwtUtil implements TokenIssuerPort {
    private final SecretKey secretKey;
    private final long accessTokenExpireMs;
    private final long refreshTokenExpireMs;

    public JwtUtil(@Value("${app.jwt.secret-key}") String secret,
                   @Value("${app.jwt.access-expire-ms}") long accessTokenExpireMs,
                   @Value("${app.jwt.refresh-expire-ms}") long refreshTokenExpireMs) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessTokenExpireMs = accessTokenExpireMs;
        this.refreshTokenExpireMs = refreshTokenExpireMs;
    }

    @Override
    public String createAccessToken(Long userId) {
        return Jwts.builder()
                .claim("user_id", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpireMs))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String createRefreshToken(Long userId) {
        return Jwts.builder()
                .claim("user_id", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpireMs))
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token){
        try{
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        }
        catch (ExpiredJwtException e) {
            throw new JwtExpiredException(EXPIRED_JWT);
        }
        catch (MalformedJwtException | UnsupportedJwtException | SecurityException e) {
            throw new InvalidJwtException(INVALID_JWT);
        }
        catch (IllegalArgumentException e) {
            throw new JwtNotFoundException(JWT_NOT_FOUND);
        }
    }
}
