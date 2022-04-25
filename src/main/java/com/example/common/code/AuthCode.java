package com.example.common.code;

public enum AuthCode {
    AU001("로그인에 성공하였습니다."),
    AU002("로그인에 실패하였습니다.");

    private final String message;

    AuthCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
