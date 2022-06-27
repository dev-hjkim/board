package com.example.common.exception;

import com.example.common.dto.ResultType;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(ResultType.UNKNOWN_USER, "The user was not found.");
    }
}
