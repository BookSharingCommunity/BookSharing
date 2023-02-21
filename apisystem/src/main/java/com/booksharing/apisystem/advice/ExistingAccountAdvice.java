package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.ExistingAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExistingAccountAdvice {
    @ResponseBody
    @ExceptionHandler(ExistingAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String existingAccountHandler(ExistingAccountException err) {
        return err.getMessage();
    }
}
