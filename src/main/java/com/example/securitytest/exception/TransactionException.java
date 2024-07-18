package com.example.securitytest.exception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class TransactionException extends RuntimeException{
    private static Logger logger = Logger.getLogger(TransactionException.class);
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public String exceptionHander(Exception exception){
        logger.error(exception);
        return "404";
    }
    @ExceptionHandler(value = {Exception.class})
    public String exceptionALl(Exception ex){
        logger.error(ex);
        return "error";
    }
}
