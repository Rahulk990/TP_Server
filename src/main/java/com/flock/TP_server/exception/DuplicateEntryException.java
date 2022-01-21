package com.flock.TP_server.exception;

public class DuplicateEntryException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(String msg) {
        super(msg);
    }
}
