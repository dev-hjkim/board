package com.example.common.exception;

import com.example.common.dto.ResultType;
import lombok.Getter;

public class BaseException extends RuntimeException {

    @Getter
    private final ResultType resultType;

    public BaseException(ResultType resultType, String message) {
        super(message);
        this.resultType = resultType;
    }
}
