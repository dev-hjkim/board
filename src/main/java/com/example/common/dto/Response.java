package com.example.common.dto;

import com.example.common.code.AuthCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Response {
    boolean success;
    String code;
    String message;
    Object data;

    public Response(boolean success, AuthCode authCode) {
        this.success = success;
        this.code = authCode.name();
        this.message = authCode.getMessage();
    }

    public Response(boolean success, AuthCode authCode, Object data) {
        this.success = success;
        this.code = authCode.name();
        this.message = authCode.getMessage();
        this.data = data;
    }
}
