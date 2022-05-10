package com.example.common.dto;

public enum ResultType {
    OK("200000", "OK"),

    NO_CONTENT("204001", "No content"),

    MISSING_PARAMETER("400003", "Missing parameter"),
    INVALID_PARAMETER("400004", "Invalid parameter"),
    USER_ALREADY_ENROLLED("400005", "User already enrolled"),

    UNKNOWN_USER("401001", "Unknown user"),
    ACCESS_TOKEN_REQUIRED("401002", "Access token required"),
    INVALID_TOKEN("401003", "Invalid token"),
    EXPIRED_ACCESS_TOKEN("401004", "Expired access token"),
    REFRESH_TOKEN_REQUIRED("401005", "Refresh token required"),
    EXPIRED_REFRESH_TOKEN("401006", "Expired refresh token"),

    PAGE_NOT_FOUND("404001", "Page not found"),

    METHOD_NOT_ALLOWED("405001", "Http method is not allowed"),

    DATA_ALREADY_EXIST("500001", "Data already exist"),
    NOT_ALLOWED_OPERATION("500002", "Not allowed operation");

    private final String code;
    private final String message;

    ResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}