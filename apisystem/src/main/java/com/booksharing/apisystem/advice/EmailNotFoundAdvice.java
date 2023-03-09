package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String emailNotFoundHandler(EmailNotFoundException err) {
        return err.getMessage();
    }
}
