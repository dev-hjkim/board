package com.example.advice;

import com.example.common.dto.ErrorResult;
import com.example.common.dto.ResultType;
import com.example.common.exception.ExpiredTokenException;
import com.example.common.exception.TokenRequiredException;
import com.example.common.exception.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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
    public ResultType handleWrongMethod(HttpRequestMethodNotSupportedException ex) {
        log.error("handleWrongMethod ex :::", ex);

        return ResultType.METHOD_NOT_ALLOWED;
    }

    // 필수 파라미터 아예 없을 때(파라미터 중 아무것도 전달하지 않음)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResultType handleMissingGetReqParam(MissingServletRequestParameterException ex) {
        log.error("handleMissingGetReqParam ex :::", ex);

        return ResultType.MISSING_PARAMETER;
    }

    // validation 조건 만족하지 못한 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultType handleMissingPostReqParam(MethodArgumentNotValidException ex) {
        log.error("handleMissingPostReqParam ex :::", ex);

        return ResultType.INVALID_PARAMETER;
    }

    // DB: 중복 데이터
    @ExceptionHandler({DuplicateKeyException.class})
    public ResultType handleDBDuplicateError(DuplicateKeyException ex) {
        log.error("handleDBDuplicateError ex :::", ex);

        return ResultType.DATA_ALREADY_EXIST;
    }

    // DB: not-null 항목 안넣은 경우
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResultType handleDBIntegrityError(DataIntegrityViolationException ex) {
        log.error("handleDBIntegrityError ex :::", ex);

        return ResultType.NOT_ALLOWED_OPERATION;
    }

    // JWT Token이 만료된 경우
    @ExceptionHandler({ExpiredJwtException.class})
    public ResultType handleExpiredToken(ExpiredJwtException ex) {
        log.error("handleExpiredToken ex :::", ex);

        if (ex.getMessage().equals("ACCESS")) {
            return ResultType.EXPIRED_ACCESS_TOKEN;
        } else {
            return ResultType.EXPIRED_REFRESH_TOKEN;
        }
    }

    // JWT Token이 잘못된 형식이거나 파싱하는 데 에러가 발생하는 경우
    @ExceptionHandler({JwtException.class})
    public ResultType handleMalformedToken(JwtException ex) {
        log.error("handleMalformedToken ex :::", ex);

        return ResultType.INVALID_TOKEN;
    }

    // token이 누락된 경우
    @ExceptionHandler({TokenRequiredException.class})
    public ResultType handleTokenRequired(TokenRequiredException ex) {
        log.error("handleTokenRequired ex :::", ex);

        return ex.getResultType();
    }

    // token이 만료된 경우
    @ExceptionHandler({ExpiredTokenException.class})
    public ResultType handleExpiredToken(ExpiredTokenException ex) {
        log.error("handleExpiredToken ex :::", ex);
        
        return ex.getResultType();
    }

    // user 조회 결과가 null인 경우
    @ExceptionHandler({UserNotFoundException.class})
    public ResultType handleUserNotFound(UserNotFoundException ex) {
        log.error("handleUserNotFound ex :::", ex);

        return ResultType.UNKNOWN_USER;
    }
}
