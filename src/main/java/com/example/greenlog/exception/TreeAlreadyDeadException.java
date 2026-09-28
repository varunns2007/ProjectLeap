package com.example.greenlog.exception;

public class TreeAlreadyDeadException extends RuntimeException {

    public TreeAlreadyDeadException(String message) {
        super(message);
    }
}