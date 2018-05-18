package com.expense.manager.enums;

public enum Type {

    INCOME("income"),

    EXPENSE("expense"),

    INVESTMENT("investment"),

    INCOME_AND_EXPENSE("income_and_expense");

    private String name;

    private Type(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static Type value(String value){
        for (Type type : Type.values()){
            if(type.name.equalsIgnoreCase(value)){
                return type;
            }
        }
        return null;
    }

}
