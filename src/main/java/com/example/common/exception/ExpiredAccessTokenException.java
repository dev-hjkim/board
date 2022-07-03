package com.example.common.exception;

import com.example.common.dto.ResultType;

public class ExpiredAccessTokenException extends BaseException {

    private static final long serialVersionUID = -5691456153564264228L;

    public ExpiredAccessTokenException() {
        super(ResultType.EXPIRED_ACCESS_TOKEN, "Access token is expired");
    }
}
