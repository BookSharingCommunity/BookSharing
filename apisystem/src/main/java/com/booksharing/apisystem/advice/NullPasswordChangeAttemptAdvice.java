package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.NullPasswordChangeAttemptException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NullPasswordChangeAttemptAdvice {
    @ResponseBody
    @ExceptionHandler(NullPasswordChangeAttemptException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String nullLoginAttemptHandler(NullPasswordChangeAttemptException err) {
        return err.getMessage();
    }
}
