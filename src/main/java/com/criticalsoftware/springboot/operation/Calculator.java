package com.criticalsoftware.springboot.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

interface MathOperation {	
	double operate(double value1, double value2);
}

/**
 * Enumeration with supported arithmetic operations and it's calculation method.
 * 
 * @author João Santos
 * @version 1.0
 */
public class Calculator {
	
	private Map<String, MathOperation> operations = new HashMap<String, MathOperation>();	
	
	public Calculator() {
		super();
		populateOperationMap();
	}	
	
    public double calculate(double value1, double value2, String operationName) {
    	return operations.get(operationName.toUpperCase()).operate(value1, value2);
    }
    
    public Set<String> getValidOperations(){
    	return operations.keySet();
    }
    
    private void populateOperationMap() {    	
    	operations.put("SUM", (value1, value2) -> value1 + value2);
    	operations.put("SUBTRACT", (value1, value2) -> value1 - value2);
    	operations.put("MULTIPLICATION", (value1, value2) -> value1 * value2);
    	operations.put("DIVISION", (value1, value2) -> value1 / value2);
    	operations.put("AVG", (value1, value2) -> (value1 + value2) / 2);
    }       
}

