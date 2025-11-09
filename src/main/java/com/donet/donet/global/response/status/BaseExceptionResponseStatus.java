package com.donet.donet.global.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus{
    // 1000 : 성공 및 공통 에러
    SUCCESS(HttpStatus.OK.value(), 1000, "요청에 성공했습니다."),

    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),1001, "유효하지 않은 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), 1002, "인증 자격이 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), 1003, "권한이 없습니다."),
    API_NOT_FOUND(HttpStatus.NOT_FOUND.value(),1004, "존재하지 않는 API입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), 1005, "유효하지 않은 Http 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1006, "서버 내부 오류입니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED.value(), 1007, "올바르지 않은 토큰입니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED.value(), 1008, "만료된 토큰입니다"),
    JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED.value(), 1009, "토큰을 찾을 수 없습니다"),

    // 2000 : Auth 관련 에러
    OAUTH_SERVER_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), 2001, "OAuth 제공자 서버로 인해 요청을 처리할 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 2002, "일치하는 리프레시 토큰이 존재하지 않습니다"),
    LOGOUTED_USER(HttpStatus.UNAUTHORIZED.value(), 2003, "로그아웃된 사용자입니다."),

    //3000 : Donation 관련 에러
    NO_MATCH_DONATION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 3000, "id에 일치하는 기부가 없습니다."),
    FAIL_TO_LOAD_DONATION_DETAIL(HttpStatus.SERVICE_UNAVAILABLE.value(), 3003, "기부 상세페이지 조회 실패했습니다."),
    NO_MATCH_PARTNER(HttpStatus.INTERNAL_SERVER_ERROR.value(), 3004, "id에 일치하는 파트너사가 없습니다."),
    NO_MATCH_CATEGORY(HttpStatus.INTERNAL_SERVER_ERROR.value(), 3005, "없는 카테고리 이름입니다."),
    NOT_FOUND_RECOMMEND_DONATION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 3006, "추천 기부를 찾을 수 없습니다."),
    NOT_FOUND_POPULAR_DONATION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 3007, "인기 기부를 찾을 수 없습니다."),

    // 4000 : User 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 4001, "존재하지 않는 유저입니다."),
    USER_DOMAIN_RULE_VIOLATION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 4002, "유저 도메인 규칙 위반입니다."),
    PROFILE_IMG_UPLOADING_FAILED(HttpStatus.SERVICE_UNAVAILABLE.value(), 4003, "일시적 오류로 사용자 프로필 이미지 업로드에 실패했습니다."),

    // 6000 : Review 관련 에러
    REVIEW_IMAGE_UPLOAD_FAILED(HttpStatus.SERVICE_UNAVAILABLE.value(), 6000, "일시적 오류로 리뷰 이미지 업로드에 실패했습니다."),
    DONATION_REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 6001, "존재하지 않는 기부후기입니다.");

    private final int status;
    private final int code;
    private final String message;


    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
