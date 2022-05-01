package com.example.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class Result {
    String code;
    String message;
    Object data;

    public Result(ResultType resultType) {
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }

    public Result(ResultType resultType, Object data) {
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
        this.data = data;
    }

    public HttpStatus parseHttpCode() {
        return HttpStatus.valueOf(Integer.parseInt(this.code.substring(0, 3)));
    }
}
