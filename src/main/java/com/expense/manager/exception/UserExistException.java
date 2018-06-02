package com.expense.manager.exception;

public class UserExistException extends RuntimeException {

    private int code;

    public UserExistException(String message, int code){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
