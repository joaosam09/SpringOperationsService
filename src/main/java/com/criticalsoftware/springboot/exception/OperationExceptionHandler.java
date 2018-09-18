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

/**
 * Custom exception handler for package "com.criticalsoftware.springboot.operation".
 * @author João Santos
 * @version 1.0
 */
@ControllerAdvice(basePackages = "com.criticalsoftware.springboot.operation")
public class OperationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("ApplicationFileLogger"); //Logger declared in resources/logback.xml
	
	/**
	 * Handles exceptions of type "HttpMessageNotReadableException".
	 * @param ex The caught exception
	 * @param headers HttpHeaders of the request
	 * @param status HttpStatus of the request
	 * @param request The WebRequest
	 * @return ResponseEntity<Object> Returns new ResponseEntity<Object> instance with error details and HttpStatus.BAD_REQUEST
	 */
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {        
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid JSON request", ex.getLocalizedMessage());
        LOGGER.error("Status code " + HttpStatus.BAD_REQUEST.value() + ": " + ex.getMessage());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
		
	/**
	 * Handles exceptions of type "OperationNotFoundException".
	 * @param ex The caught exception	 
	 * @param request The WebRequest
	 * @return ResponseEntity<Object> Returns new ResponseEntity<Object> instance indicating a non-existent operation and HttpStatus.NOT_FOUND
	 */
	@ExceptionHandler(OperationNotFoundException.class)
	public final ResponseEntity<Object> handleOperationNotFoundException(OperationNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Operation not found", ex.getMessage());
		LOGGER.error("Status code " + HttpStatus.NOT_FOUND.value() + ": " + ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
			
	/**
	 * Handles all exceptions.
	 * @param ex The caught exception
	 * @param request The WebRequest
	 * @return ResponseEntity<Object> Returns new ResponseEntity<Object> instance with error details and HttpStatus.INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error", ex.getMessage());
		LOGGER.error("Status code " + HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}		
}