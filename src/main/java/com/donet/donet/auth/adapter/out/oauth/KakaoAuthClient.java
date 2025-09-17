package com.donet.donet.auth.adapter.out.oauth;

import com.donet.donet.auth.application.port.out.KakaoAuthPort;
import com.donet.donet.auth.application.port.out.KakaoOAuthToken;
import com.donet.donet.auth.application.port.out.KakaoUserProfile;
import com.donet.donet.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.OAUTH_SERVER_UNAVAILABLE;


@Component
@RequiredArgsConstructor
public class KakaoAuthClient implements KakaoAuthPort {
    @Value("${app.kakao.auth.client}") private String client;
    @Value("${app.kakao.auth.redirect}") private String redirect;

    private RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }

    @Override
    public KakaoOAuthToken requestToken(String accessCode) {
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client);
        params.add("redirect_uri", redirect);
        params.add("code", accessCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                KakaoOAuthToken.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(OAUTH_SERVER_UNAVAILABLE);
        }

        KakaoOAuthToken body = response.getBody();
        if(body == null){
            throw new CustomException(OAUTH_SERVER_UNAVAILABLE);
        }
        return body;
    }

    @Override
    public KakaoUserProfile requestUserProfile(KakaoOAuthToken oAuthToken) {
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+ oAuthToken.access_token());
        headers.add("Accept", "application/json");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity <>(headers);

        ResponseEntity<KakaoUserProfile> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                KakaoUserProfile.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(OAUTH_SERVER_UNAVAILABLE);
        }

        KakaoUserProfile body = response.getBody();
        if(body == null){
            throw new CustomException(OAUTH_SERVER_UNAVAILABLE);
        }
        return body;
    }
}
