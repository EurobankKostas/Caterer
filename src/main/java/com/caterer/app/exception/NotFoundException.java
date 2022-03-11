package com.caterer.app.exception;

public class NotFoundException extends Exception{

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
