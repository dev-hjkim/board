package com.example.common.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("There is no data");
    }
}
