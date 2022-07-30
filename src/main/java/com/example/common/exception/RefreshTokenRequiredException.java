package com.example.common.exception;

import com.example.common.dto.ResultType;

public class RefreshTokenRequiredException extends BaseException {

    private static final long serialVersionUID = 5600656707384112141L;

    public RefreshTokenRequiredException(String message) {
        super(ResultType.REFRESH_TOKEN_REQUIRED, message);
    }
}
