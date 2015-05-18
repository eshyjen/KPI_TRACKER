package com.ericsson.ipm.v1.exception;

@SuppressWarnings("serial")
public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
