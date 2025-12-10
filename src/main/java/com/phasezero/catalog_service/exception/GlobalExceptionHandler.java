package com.phasezero.catalog_service.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataExistsException.class)
	public ResponseEntity<Object> handle(DataExistsException exception) {
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body(Map.of("error", exception.getMessage()));
	}

}
