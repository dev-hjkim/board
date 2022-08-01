package com.example.common.exception;

import com.example.common.dto.ResultType;

public class NoAuthorityException extends BaseException {

    private static final long serialVersionUID = 323183528761167051L;

    public NoAuthorityException() {
        super(ResultType.NO_ROLE);
    }
}
