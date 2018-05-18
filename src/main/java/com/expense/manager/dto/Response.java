package com.expense.manager.dto;

import java.io.Serializable;

public class Response implements Serializable {

    private int code;
    private String message;
    private String errorMessage;
    private Object data;

    public Response(int code, String message, String errorMessage, Object data) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
