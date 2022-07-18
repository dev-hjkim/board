package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private ResultType resultType;

    @JsonIgnore
    private HttpStatus status;
    private String code;
    private String message;
    private Object data;

    public Result(ResultType resultType) {
        this.resultType = resultType;
    }

    public Result(Object data) {
        this.resultType = ResultType.OK;
        this.data = data;
    }


    public HttpStatus getStatus() {
        return this.resultType.getStatus();
    }

    public String getCode() {
        return this.resultType.getCode();
    }

    public String getMessage() {
        return this.resultType.getMessage();
    }

    public Object getData() {
        return this.data;
    }
}
