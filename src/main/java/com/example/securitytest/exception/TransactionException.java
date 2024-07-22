package com.example.securitytest.exception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
/**
 * Global exception handler for the application.
 * Handles specific exceptions and returns appropriate error views.
 */
@ControllerAdvice
public class TransactionException extends RuntimeException{
    private static Logger logger = Logger.getLogger(TransactionException.class);
    /**
     * Handles NoHandlerFoundException (e.g., 404 Not Found errors).
     *
     * @param exception the exception thrown when no handler is found
     * @return the name of the view to be returned for 404 errors
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public String exceptionHander(Exception exception){
        logger.error(exception);
        return "404";
    }
    /**
     * Handles all other exceptions.
     *
     * @param ex the exception thrown
     * @return the name of the view to be returned for general errors
     */
    @ExceptionHandler(value = {Exception.class})
    public String exceptionALl(Exception ex){
        logger.error(ex);
        return "error";
    }
}
