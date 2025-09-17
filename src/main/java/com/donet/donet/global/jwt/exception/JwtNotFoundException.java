package com.donet.donet.global.jwt.exception;

import com.donet.donet.global.exception.CustomException;
import com.donet.donet.global.response.status.ResponseStatus;

public class JwtNotFoundException extends CustomException {
    public JwtNotFoundException(ResponseStatus exceptionStatus){
        super(exceptionStatus);
    }
}
