package com.example.common.exception;

import com.example.common.dto.ResultType;

public class DataNotFoundException extends BaseException {
    public DataNotFoundException() {
        super(ResultType.DATA_NOT_FOUND, "There is no data");
    }
}
