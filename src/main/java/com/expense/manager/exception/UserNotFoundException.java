package com.expense.manager.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
