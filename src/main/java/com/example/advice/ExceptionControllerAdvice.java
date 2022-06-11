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

    // NPE
    @ExceptionHandler({NullPointerException.class})
    public ErrorResult handleNullError(NullPointerException ex) {
        log.error("handleNullError ex :::", ex);

        if (ex.getMessage().equals(ResultType.UNKNOWN_USER.getCode())) {
            return new ErrorResult(ResultType.UNKNOWN_USER);
        } else if (ex.getMessage().equals(ResultType.ACCESS_TOKEN_REQUIRED.getCode())) {
            return new ErrorResult(ResultType.ACCESS_TOKEN_REQUIRED);
        } else if (ex.getMessage().equals(ResultType.REFRESH_TOKEN_REQUIRED.getCode())) {
            return new ErrorResult(ResultType.REFRESH_TOKEN_REQUIRED);
        } else {
            return new ErrorResult(ResultType.NO_CONTENT);
        }
    }

    // JWT Token이 만료된 경우
    @ExceptionHandler({ExpiredJwtException.class})
    public ErrorResult handleExpiredToken(ExpiredJwtException ex) {
        log.error("handleExpiredToken ex :::", ex);

        if (ex.getMessage().equals("ACCESS")) {
            return new ErrorResult(ResultType.EXPIRED_ACCESS_TOKEN);
        } else {
            return new ErrorResult(ResultType.EXPIRED_REFRESH_TOKEN);
        }
    }

    
    // JWT Token이 잘못된 형식이거나 파싱하는 데 에러가 발생하는 경우
    @ExceptionHandler({JwtException.class})
    public ErrorResult handleMalformedToken(JwtException ex) {
        log.error("handleMalformedToken ex :::", ex);

        return new ErrorResult(ResultType.INVALID_TOKEN);
    }

    // token이 누락된 경우
    @ExceptionHandler({TokenRequiredException.class})
    public ErrorResult handleTokenRequired(TokenRequiredException ex) {
        log.error("handleTokenRequired ex :::", ex);

        return new ErrorResult(ex.getResultType());
    }

    // token이 만료된 경우
    @ExceptionHandler({ExpiredTokenException.class})
    public ErrorResult handleExpiredToken(ExpiredTokenException ex) {
        log.error("handleExpiredToken ex :::", ex);
        
        return new ErrorResult(ex.getResultType());
    }

    // user 조회 결과가 null인 경우
    @ExceptionHandler({UserNotFoundException.class})
    public ErrorResult handleUserNotFound(UserNotFoundException ex) {
        log.error("handleUserNotFound ex :::", ex);

        return new ErrorResult(ResultType.UNKNOWN_USER);
    }
}
