package com.donet.donet.global.exception;

import com.donet.donet.global.response.status.ResponseStatus;

public class DonationException extends CustomException {
    public DonationException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
