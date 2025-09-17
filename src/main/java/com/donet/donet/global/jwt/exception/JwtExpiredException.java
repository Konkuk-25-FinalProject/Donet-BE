package com.donet.donet.global.jwt.exception;

import com.donet.donet.global.exception.CustomException;
import com.donet.donet.global.response.status.ResponseStatus;

public class JwtExpiredException extends CustomException {
    public JwtExpiredException(ResponseStatus exceptionStatus){
        super(exceptionStatus);
    }
}
