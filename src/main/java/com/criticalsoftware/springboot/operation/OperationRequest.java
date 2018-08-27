package com.criticalsoftware.springboot.operation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class representing an operation request.
 * Contains 2 values and the operation to be calculated.
 *  
 * @author João Santos
 * @version 1.0
 */
public class OperationRequest {	
	private double value1;	
	private double value2;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String operation;
	
	public OperationRequest() {
		super();
	}	
	
	public OperationRequest(double value1, double value2, String operation) {		
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.operation = operation;
	}
	
	public double getValue1() {
		return value1;
	}
	
	public void setValue1(double value) {
		this.value1 = value;
	}
	
	public double getValue2() {
		return value2;
	}
	
	public void setValue2(double value) {
		this.value2 = value;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}		
}
