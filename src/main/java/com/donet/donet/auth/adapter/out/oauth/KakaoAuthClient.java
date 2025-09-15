package com.donet.donet.auth.adapter.out.oauth;

import com.donet.donet.auth.application.port.out.KakaoAuthPort;
import com.donet.donet.auth.application.port.out.KakaoOAuthToken;
import com.donet.donet.auth.application.port.out.KakaoUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class KakaoAuthClient implements KakaoAuthPort {
    @Value("${app.kakao.auth.client}") private String client;
    @Value("${app.kakao.auth.redirect}") private String redirect;

    @Override
    public KakaoOAuthToken requestToken(String accessCode) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client);
        params.add("redirect_url", redirect);
        params.add("code", accessCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                KakaoOAuthToken.class);

        return response.getBody();
    }

    @Override
    public KakaoUserProfile requestUserProfile(KakaoOAuthToken oAuthToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+ oAuthToken.access_token());

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity <>(headers);

        ResponseEntity<KakaoUserProfile> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                KakaoUserProfile.class);

        return response.getBody();
    }
}
