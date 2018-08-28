package com.criticalsoftware.springboot.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.criticalsoftware.springboot.operation.OperationNotFoundException;

@ControllerAdvice(basePackages = "com.criticalsoftware.springboot.operation")
public class OperationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("FileLogger"); //Logger declared in resources/logback.xml
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {        
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid JSON request", ex.getLocalizedMessage());
        LOGGER.error("Status code " + HttpStatus.BAD_REQUEST.value() + ": " + ex.getMessage());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(OperationNotFoundException.class)
	public final ResponseEntity<Object> handleOperationNotFoundException(OperationNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Operation not found", ex.getMessage());
		LOGGER.error("Status code " + HttpStatus.NOT_FOUND.value() + ": " + ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error", ex.getMessage());
		LOGGER.error("Status code " + HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}		
}
