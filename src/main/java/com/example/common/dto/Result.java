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
    private HttpStatus status;

    private String code;
    private String message;
    private Object data;

    public Result(ResultType resultType) {
        this.status = resultType.getStatus();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }

    public Result(Object data) {
        this.status = HttpStatus.OK;
        this.code = "200000";
        this.message = "OK";
        this.data = data;
    }
}
