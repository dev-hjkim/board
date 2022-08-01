package com.example.common.exception;

import com.example.common.dto.ResultType;

public class UserNotFoundException extends BaseException {

    private static final long serialVersionUID = -4173602124772681912L;

    public UserNotFoundException() {
        super(ResultType.UNKNOWN_USER);
    }
}
