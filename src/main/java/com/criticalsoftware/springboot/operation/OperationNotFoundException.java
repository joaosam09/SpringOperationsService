package com.criticalsoftware.springboot.operation;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Custom Exception for not found operation
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OperationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1827067681610860799L;

	public OperationNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
