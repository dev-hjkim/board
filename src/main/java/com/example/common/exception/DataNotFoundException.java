package com.example.common.exception;

import com.example.common.dto.ResultType;

public class DataNotFoundException extends BaseException {

    private static final long serialVersionUID = -7530170088983506216L;

    public DataNotFoundException() {
        super(ResultType.DATA_NOT_FOUND);
    }
}
