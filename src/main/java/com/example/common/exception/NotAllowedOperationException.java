package com.example.common.exception;

import com.example.common.dto.ResultType;

public class NotAllowedOperationException extends BaseException {

    private static final long serialVersionUID = -2759656177973979953L;

    public NotAllowedOperationException() {
        super(ResultType.NOT_ALLOWED_OPERATION);
    }
}
