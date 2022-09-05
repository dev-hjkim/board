package com.example.advice;

import com.example.common.dto.ExceptionResult;
import com.example.common.dto.ResultType;
import com.example.common.exception.*;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class ExceptionControllerAdvice {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    // method 잘못된 경우
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ExceptionResult handleWrongMethod(HttpRequestMethodNotSupportedException ex) {
        log.error("handleWrongMethod ex :::", ex);

        return processException(ResultType.METHOD_NOT_ALLOWED);
    }

    // 필수 파라미터 아예 없을 때(파라미터 중 아무것도 전달하지 않음)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ExceptionResult handleMissingGetReqParam(MissingServletRequestParameterException ex) {
        log.error("handleMissingGetReqParam ex :::", ex);

        return processException(ResultType.MISSING_PARAMETER);
    }

    // validation 조건 만족하지 못한 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResult handleMissingPostReqParam(MethodArgumentNotValidException ex) {
        log.error("handleMissingPostReqParam ex :::", ex);

        return processException(ResultType.INVALID_PARAMETER);
    }

    // DB: 중복 데이터
    @ExceptionHandler({DuplicateKeyException.class})
    public ExceptionResult handleDBDuplicateError(DuplicateKeyException ex) {
        log.error("handleDBDuplicateError ex :::", ex);

        return processException(ResultType.DATA_ALREADY_EXIST);
    }

    // DB: not-null 항목 안넣은 경우
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ExceptionResult handleDBIntegrityError(DataIntegrityViolationException ex) {
        log.error("handleDBIntegrityError ex :::", ex);

        return processException(ResultType.NOT_ALLOWED_OPERATION);
    }

    // JWT Token이 잘못된 형식이거나 파싱하는 데 에러가 발생하는 경우
    @ExceptionHandler({JwtException.class})
    public ExceptionResult handleMalformedToken(JwtException ex) {
        log.error("handleMalformedToken ex :::", ex);

        return processException(ResultType.INVALID_TOKEN);
    }

    // 매핑되는 핸들러가 없는 경우
    @ExceptionHandler({NoHandlerFoundException.class})
    public ExceptionResult handleNoHandler(NoHandlerFoundException ex) {
        log.error("handleNoHandler ex :::", ex);

        return processException(ResultType.PAGE_NOT_FOUND);
    }

    // media type이 일치하지 않는 경우
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ExceptionResult handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        log.error("handleMediaTypeNotSupported ex :::", ex);

        return processException(ResultType.UNSUPPORTED_MEDIA_TYPE);
    }

    // accept에 명시된 media type과 일치하지 않는 경우
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ExceptionResult handleNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        log.error("handleNotAcceptable ex :::", ex);

        return processException(ResultType.NOT_ACCEPTABLE);
    }

    // customException 발생한 경우
    @ExceptionHandler({BaseException.class})
    public ExceptionResult handleBaseException(BaseException ex) {
        log.error("handleBaseException ex :::", ex);

        return ex.getExceptionResult();
    }

    private ExceptionResult processException(ResultType resultType) {
        return new ExceptionResult(resultType);
    }
}
