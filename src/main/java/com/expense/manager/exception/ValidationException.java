package com.expense.manager.exception;

public class ValidationException extends Exception {

    public ValidationException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
