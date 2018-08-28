package com.criticalsoftware.springboot.exception;

import java.util.Date;

/**
 * Class used to retrieve error details as a client response.
 * 
 * @author João Santos
 * @version 1.0
 */
public class ErrorDetails {
	private Date timestamp;	
	private String message;
	private String details;
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
