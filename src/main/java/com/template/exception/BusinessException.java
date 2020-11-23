package com.template.exception;

public class BusinessException extends Exception {

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
