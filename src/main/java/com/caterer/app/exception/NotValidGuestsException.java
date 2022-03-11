package com.caterer.app.exception;

public class NotValidGuestsException extends Exception{
    public NotValidGuestsException(String errorMessage) {
        super(errorMessage);
    }

}
