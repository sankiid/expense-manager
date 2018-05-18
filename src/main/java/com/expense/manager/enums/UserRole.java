package com.expense.manager.enums;

public enum UserRole {

    USER("USER"),

    ADMIN("ADMIN");

    private String name;

    private UserRole(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
