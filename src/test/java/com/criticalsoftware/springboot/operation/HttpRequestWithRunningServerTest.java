package com.criticalsoftware.springboot.operation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.criticalsoftware.springboot.exception.ErrorDetails;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the service mocking the web environment.
 * 
 * @author João Santos
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestWithRunningServerTest {

	@LocalServerPort
    private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	/**
	 * Tests if a request with 2 values and an operation creates a response with result, date and status code = 200.
	 */
	@Test
	public void requestShouldReturnResponseWithResultAndDate() {		
		ResponseEntity<OperationResponse> response = restTemplate.postForEntity("http://localhost:" + port + "/calculate",
															  new OperationRequest(10,20,"sum"), OperationResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getResult()).isEqualTo(30);
		assertThat(response.getBody().getTime()).isNotEqualTo(null);
	}
	
	/**
	 * Tests if a request with an unknown operation creates a response with status code = 404.
	 */
	@Test
	public void requestWithNonExistentOperationShouldReturnErrorMessage() {		
		ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("http://localhost:" + port + "/calculate",
															  		 new OperationRequest(10,20,"qggwq"), ErrorDetails.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("Operation not found");		
	}
	
	/**
	 * Tests if an invalid request creates a response with status code = 400.
	 */
	@Test
	public void requestWithEmptyRequestShouldReturnErrorMessage() {		
		ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("http://localhost:" + port + "/calculate",
															  		 new OperationRequest(), ErrorDetails.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}
