package com.java.user.crud.project.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserInputException extends GenericException{
    public InvalidUserInputException(String message, HttpStatus httpStatus){
        super (message,httpStatus);
    }
}
