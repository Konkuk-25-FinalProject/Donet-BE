package com.donet.donet.global.exception;

import com.donet.donet.global.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public CustomException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
