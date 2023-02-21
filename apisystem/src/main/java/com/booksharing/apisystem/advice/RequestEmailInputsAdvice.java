package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.EmailNotFoundException;
import com.booksharing.apisystem.exceptions.RequestEmailInputsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RequestEmailInputsAdvice {
    @ResponseBody
    @ExceptionHandler(RequestEmailInputsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String emailNotFoundHandler(RequestEmailInputsException err) {
        return err.getMessage();
    }
}
