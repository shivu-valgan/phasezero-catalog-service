package com.phasezero.catalog_service.exception;


public class DataExistsException extends RuntimeException {

    public DataExistsException(String message) {
        super(message);
    }

    public DataExistsException() {
        super("Data already exists");
    }
}
