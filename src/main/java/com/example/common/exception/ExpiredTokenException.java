package com.example.common.exception;


import com.example.common.dto.ResultType;
import lombok.Getter;

public class ExpiredTokenException extends RuntimeException {
    @Getter
    ResultType resultType;

    public ExpiredTokenException(String message) {
        super(message);

        if (message.equals("ACCESS")) {
            resultType = ResultType.EXPIRED_ACCESS_TOKEN;
        } else {
            resultType = ResultType.EXPIRED_REFRESH_TOKEN;
        }
    }
}
