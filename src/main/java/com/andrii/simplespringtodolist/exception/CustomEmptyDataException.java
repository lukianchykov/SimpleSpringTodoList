package com.andrii.simplespringtodolist.exception;

public class CustomEmptyDataException extends RuntimeException{

    public CustomEmptyDataException(String message) {
        super(message);
    }


}
