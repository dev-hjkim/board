package com.example.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultType {
    OK(HttpStatus.OK, "200000", "OK"),

    NO_CONTENT(HttpStatus.NO_CONTENT, "204001", "No content"),

    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "400003", "Missing parameter"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "400004", "Invalid parameter"),
    USER_ALREADY_ENROLLED(HttpStatus.BAD_REQUEST, "400005", "User already enrolled"),

    UNKNOWN_USER(HttpStatus.UNAUTHORIZED, "401001", "Unknown user"),
    ACCESS_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "401002", "Access token required"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "401003", "Invalid token"),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "401004", "Expired access token"),
    REFRESH_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "401005", "Refresh token required"),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "401006", "Expired refresh token"),
    NO_ROLE(HttpStatus.UNAUTHORIZED, "401007", "No Role"),

    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "404001", "Page not found"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "404002", "Data not found"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "405001", "Http method is not allowed"),

    DATA_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "500001", "Data already exist"),
    NOT_ALLOWED_OPERATION(HttpStatus.INTERNAL_SERVER_ERROR, "500002", "Not allowed operation");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ResultType(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}