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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CalculateHttpRequestWithRunningServerTest {

	@LocalServerPort
    private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void requestShouldReturnResponseWithResultAndDate() {		
		ResponseEntity<OperationResponse> response = restTemplate.postForEntity("http://localhost:" + port + "/calculate",
															  new OperationRequest(10,20,"add"), OperationResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getResult()).isEqualTo(30);
		assertThat(response.getBody().getTime()).isNotEqualTo(null);
	}
	
	@Test
	public void requestWithNonExistentOperationShouldReturnErrorMessage() {		
		ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("http://localhost:" + port + "/calculate",
															  		 new OperationRequest(10,20,"qggwq"), ErrorDetails.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("Operation not found");		
	}
}
