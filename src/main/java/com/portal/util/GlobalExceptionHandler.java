package com.portal.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portal.models.ErrorModel;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice(basePackages = "com.")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handle(Exception exp) {
        log.error(exp.getMessage(), exp);
        if (exp instanceof HttpStatusCodeException) {
            HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) exp;
            String errorMessage = httpStatusCodeException.getMessage();
            HttpStatus httpStatus = httpStatusCodeException.getStatusCode();
            return new ResponseEntity<>(new ErrorModel(httpStatus, errorMessage), httpStatus);
        } else {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, exp.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
