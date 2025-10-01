package com.donet.donet.global.swagger;

import com.donet.donet.global.response.status.BaseExceptionResponseStatus;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.*;


@Getter
public enum SwaggerResponseDescription {
    DEFAULT(new LinkedHashSet<>()),

    REISSUE_TOKEN(new LinkedHashSet<>(Set.of(
            INVALID_JWT,
            USER_NOT_FOUND,
            REFRESH_TOKEN_NOT_FOUND
    ))),

    EDIT_USER_PROFILE(new LinkedHashSet<>(Set.of(
            USER_NOT_FOUND,
            PROFILE_IMG_UPLOADING_FAILED
    ))),

    LOGOUT(new LinkedHashSet<>(Set.of(
            USER_NOT_FOUND
    )));

    private final Set<BaseExceptionResponseStatus> exceptionResponseStatusSet;

    SwaggerResponseDescription(Set<BaseExceptionResponseStatus> exceptionResponseStatusSet) {
        exceptionResponseStatusSet.addAll(new LinkedHashSet<>(Set.of(
                BAD_REQUEST,
                UNAUTHORIZED,
                FORBIDDEN,
                API_NOT_FOUND,
                METHOD_NOT_ALLOWED,
                INTERNAL_SERVER_ERROR,
                LOGOUTED_USER
        )));

        this.exceptionResponseStatusSet = exceptionResponseStatusSet;
    }
}
