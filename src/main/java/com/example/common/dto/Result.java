package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    @JsonIgnore
    HttpStatus status;

    String code;
    String message;
    Object data;

    public Result(ResultType resultType) {
        this.status = resultType.getStatus();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }

    public Result(ResultType resultType, Object data) {
        this.status = resultType.getStatus();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
        this.data = data;
    }
}