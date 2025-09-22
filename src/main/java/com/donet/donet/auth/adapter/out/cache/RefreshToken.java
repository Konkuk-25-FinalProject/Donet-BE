package com.donet.donet.auth.adapter.out.cache;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refresh-token-userid", timeToLive = 14440)
public class RefreshToken {
    @Id
    private Long userId;
    private String token;

    public RefreshToken(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public void changeToken(String token){
        this.token = token;
    }
}