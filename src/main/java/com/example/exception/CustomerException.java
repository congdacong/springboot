package com.example.exception;

public class CustomerException extends RuntimeException {
    private String message;
    private int code;

    private CustomerException(){

    }
    public CustomerException(CustomerExcepitonType CustomerExcepitonType, String message) {
        this.message = message;
        this.code = CustomerExcepitonType.getCode();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
