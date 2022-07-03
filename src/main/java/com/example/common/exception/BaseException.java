package com.example.common.exception;

import com.example.common.dto.ResultType;
import lombok.Getter;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1733247474863885433L;

    @Getter
    private final ResultType resultType;

    public BaseException(ResultType resultType, String message) {
        super(message);
        this.resultType = resultType;
    }
}
