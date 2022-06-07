package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult {
    @JsonIgnore
    HttpStatus status;

    String code;
    String message;

    public ErrorResult(ResultType resultType) {
        this.status = resultType.getStatus();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }
}
