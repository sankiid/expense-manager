package com.expense.manager.exception;

import org.springframework.security.core.AuthenticationException;

public class IncorrectJwtTokenException extends AuthenticationException {

    public IncorrectJwtTokenException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}