package com.criticalsoftware.springboot.operation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Serves a calculation method for different arithmetic operations.
 */
@Service
public class OperationService {
	
	/**
	 * Calculates an operation request and retrieves an operation response.
	 * @param request OperationRequest instance with the requested info.
	 * @return OperationResponse Returns an instance with the result and date of the operation.
	 * @throws OperationNotFoundException Exception thrown when the requested operation doesn't exist
	 */
	public OperationResponse calculate(OperationRequest request) throws OperationNotFoundException {															    		    		
		String requestedOperation = request.getOperation().toUpperCase();    			    
	    if(!isOperationValid(requestedOperation))	    	
	    	throw new OperationNotFoundException("Unrecognized operation \"" + requestedOperation + "\"");
	    
	    Operation newOperation = Operation.valueOf(requestedOperation);					
		double result = newOperation.calculate(request.getValue1(), request.getValue2());										
		
		return createResponse(result);							
	}
			
	/**
	 * Checks if the specified operation is valid.
	 * @param operation String with the intended operation.
	 * @return boolean Returns true if the operation valid and false if not.
	 */
	private static boolean isOperationValid(String operation) {			
		for (Operation op : Operation.values()) {
            if(op.toString().equals(operation)) 
            	return true; 
        }
		
		return false;
	}
	
	/**
	 * Creates a new instance of OperationResponse with the specified parameters and retrieves it.
	 * @param result Double value representing the result of the calculation.
	 * @param message String with the description message.
	 * @param httpCode HttpCode representing the success or failure of the operation.
	 * @return OperationResponse Retrieves the response with the specified parameters and the current date.
	 */
	private static OperationResponse createResponse(double result) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");		
		return new OperationResponse(result, dateFormat.format(new Date()));	
	}
}
