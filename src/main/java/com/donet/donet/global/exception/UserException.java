package com.donet.donet.global.exception;

import com.donet.donet.global.response.status.ResponseStatus;

public class UserException extends CustomException{
    public UserException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
