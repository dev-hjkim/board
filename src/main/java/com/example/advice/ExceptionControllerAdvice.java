package com.example.advice;

import com.example.common.dto.ErrorResult;
import com.example.common.dto.ResultType;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionControllerAdvice {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    // method 잘못된 경우
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ErrorResult handleWrongMethod(HttpRequestMethodNotSupportedException ex) {
        log.error("handleWrongMethod ex :::", ex);

        return new ErrorResult(ResultType.METHOD_NOT_ALLOWED);
    }

    // 필수 파라미터 아예 없을 때(파라미터 중 아무것도 전달하지 않음)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ErrorResult handleMissingGetReqParam(MissingServletRequestParameterException ex) {
        log.error("handleMissingGetReqParam ex :::", ex);

        return new ErrorResult(ResultType.MISSING_PARAMETER);
    }

    // validation 조건 만족하지 못한 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResult handleMissingPostReqParam(MethodArgumentNotValidException ex) {
        log.error("handleMissingPostReqParam ex :::", ex);

        return new ErrorResult(ResultType.INVALID_PARAMETER);
    }

    // DB: 중복 데이터
    @ExceptionHandler({DuplicateKeyException.class})
    public ErrorResult handleDBDuplicateError(DuplicateKeyException ex) {
        log.error("handleDBDuplicateError ex :::", ex);

        return new ErrorResult(ResultType.DATA_ALREADY_EXIST);
    }

    // DB: not-null 항목 안넣은 경우
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ErrorResult handleDBIntegrityError(DataIntegrityViolationException ex) {
        log.error("handleDBIntegrityError ex :::", ex);

        return new ErrorResult(ResultType.NOT_ALLOWED_OPERATION);
    }

    @ExceptionHandler({NullPointerException.class})
    public ErrorResult handleNullError(NullPointerException ex) {
        log.error("handleNullError ex :::", ex);

        if (ex.getMessage().equals(ResultType.UNKNOWN_USER.getCode())) {
            return new ErrorResult(ResultType.UNKNOWN_USER);
        } else {
            return new ErrorResult(ResultType.NO_CONTENT);
        }
    }
}
