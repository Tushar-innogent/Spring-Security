package com.java.user.crud.project.exceptions.handler;

import com.java.user.crud.project.exceptions.GenericException;
import com.java.user.crud.project.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(GenericException.class)
    protected ResponseEntity<ErrorResponse> handleException(GenericException exception, WebRequest request, HttpServletRequest response) {
        HttpStatus httpStatus = exception.getHttpStatus();
        Integer errorCode = httpStatus.value();
        String errorMessage = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, errorMessage, httpStatus.name());
        log.error("Exception message : {}", exception.getMessage(), exception);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request, HttpServletRequest response) throws GenericException {
        return handleException(new GenericException(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR),request,response);
    }
}
