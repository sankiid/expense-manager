package com.expense.manager.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}