package com.example.common.dto;

import com.example.common.code.AuthCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Response {
    String code;
    String message;
    Object data;

    public Response(AuthCode authCode) {
        this.code = authCode.name();
        this.message = authCode.getMessage();
    }

    public Response(AuthCode authCode, Object data) {
        this.code = authCode.name();
        this.message = authCode.getMessage();
        this.data = data;
    }
}
