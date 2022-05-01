package com.example.advice;

import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    // method 잘못된 경우
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleWrongMethod(HttpRequestMethodNotSupportedException ex) {
        log.info("handleWrongMethod ex ::: {}", (Object) ex.getStackTrace());

        Result result = new Result(ResultType.METHOD_NOT_ALLOWED);
        return new ResponseEntity<Object>(result, result.parseHttpCode());
    }

    // 필수 파라미터 아예 없을 때(파라미터 중 아무것도 전달하지 않음)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<?> handleMissingGetReqParam(MissingServletRequestParameterException ex) {
        log.info("handleMissingGetReqParam ex ::: {}", (Object) ex.getStackTrace());

        Result result = new Result(ResultType.MISSING_PARAMETER);
        return new ResponseEntity<Object>(result, result.parseHttpCode());
    }

    // validation 조건 만족하지 못한 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMissingPostReqParam(MethodArgumentNotValidException ex) {
        log.info("handleMissingPostReqParam ex ::: {}", (Object) ex.getStackTrace());

        Result result = new Result(ResultType.INVALID_PARAMETER);
        return new ResponseEntity<Object>(result, result.parseHttpCode());
    }

    // DB: 중복 데이터
    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<?> handleDBDuplicateError(DuplicateKeyException ex) {
        log.info("handleDBDuplicateError ex ::: {}", (Object) ex.getStackTrace());

        Result result = new Result(ResultType.DATA_ALREADY_EXIST);
        return new ResponseEntity<Object>(result, result.parseHttpCode());
    }

    // DB: not-null 항목 안넣은 경우
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleDBIntegrityError(DataIntegrityViolationException ex) {
        log.info("handleDBIntegrityError ex ::: {}", (Object) ex.getStackTrace());

        Result result = new Result(ResultType.NOT_ALLOWED_OPERATION);
        return new ResponseEntity<Object>(result, result.parseHttpCode());
    }
}
