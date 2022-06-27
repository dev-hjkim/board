package com.example.common.exception;

import com.example.common.dto.ResultType;

public class NoAuthorityException extends BaseException {
    public NoAuthorityException() {
        super(ResultType.NO_ROLE, "User has no authority to access data.");
    }
}
