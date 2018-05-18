package com.expense.manager.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
