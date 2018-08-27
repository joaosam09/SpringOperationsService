package com.criticalsoftware.springboot.operation;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller responsible for receiving operation requests and retrieving operation responses to the clients.
 * 
 * @author João Santos
 * @version 1.0
 */
@RestController
public class OperationController {

	@Autowired
	private OperationService operationService;
	
	/**
	 * Calculates the result of an operation between 2 values.
	 * Receives the request as a JSON request.
	 * Retrieves a JSON response with result and calculation date.
	 */
	@RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = "application/json")
	public OperationResponse calculate(@Valid @RequestBody OperationRequest request) {		
		return operationService.calculate(request);		
	}
}
