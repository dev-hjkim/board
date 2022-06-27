package com.example.common.exception;


import com.example.common.dto.ResultType;
import lombok.Getter;

public class TokenRequiredException extends RuntimeException {
    @Getter
    ResultType resultType;

    public TokenRequiredException(String message) {
        super(message);

        if (message.equals("ACCESS")) {
            resultType = ResultType.ACCESS_TOKEN_REQUIRED;
        } else {
            resultType = ResultType.REFRESH_TOKEN_REQUIRED;
        }
    }
}
