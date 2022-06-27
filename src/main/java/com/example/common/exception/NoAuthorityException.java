package com.example.common.exception;

public class NoAuthorityException extends RuntimeException {
    public NoAuthorityException() {
        super("User has no authority to access data.");
    }
}
