package com.java.user.crud.project.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GenericException{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
