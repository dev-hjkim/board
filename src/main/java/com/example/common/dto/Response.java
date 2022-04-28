package com.example.common.dto;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class Response {
    String code;
    String message;
    Object data;

    public Response(ResponseType responseType) {
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
    }

    public Response(ResponseType responseType, Object data) {
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
        this.data = data;
    }

    public HttpStatus getHttpCode() {
        return HttpStatus.valueOf(Integer.parseInt(this.code.substring(0, 3)));
    }
}
