package com.template.exception;

/**
 * Created by markiing on 26/07/17.
 */
@SuppressWarnings("serial")
public class ConsumerException extends Exception {

    public ConsumerException(String message){super(message);}
    public ConsumerException(Throwable t, String message) {
        super(message,t);
    }


}
