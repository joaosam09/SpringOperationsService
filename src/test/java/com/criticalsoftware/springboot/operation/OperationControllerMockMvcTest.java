package com.criticalsoftware.springboot.operation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.criticalsoftware.springboot.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the service mocking the web application context.
 * 
 * @author João Santos
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationControllerMockMvcTest {
		
	private MockMvc mockMvc;

    @Mock
    private OperationService service;
    
    @InjectMocks
    private OperationController operationController;
    
    @Autowired
    protected WebApplicationContext webApplicationContext;
        
    private JacksonTester<OperationRequest> jsonOperationRequest;
        
    @Before
    public void setup() {        
    	MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                  .build();
    }
    
    /**
	 * Tests if a request with an unknown operation creates a response with status code = 404 and "Not found" error message.
	 */
    @Test
    public void requestWithUnknownOperationShouldReturnNotFoundErrorMessage() throws Exception {
    	OperationRequest request = new OperationRequest(10, 20, "newOp");    	  	
    	
    	MvcResult result = mockMvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON)
    					   .content(jsonOperationRequest.write(request).getJson()))
    					   .andExpect(status().isNotFound())
    					   .andReturn();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	ErrorDetails errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDetails.class);
    	Assert.assertEquals(errorResponse.getMessage(), "Operation not found");    	
    }	
    
    /**
	 * Tests if an invalid request creates a response with status code = 400.
	 */
    @Test
    public void invalidRequestShouldReturnBadRequestStatus() throws Exception {
    	OperationRequest request = new OperationRequest();    	  	
    	
    	mockMvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON)
	    .content(jsonOperationRequest.write(request).getJson()))
	    .andExpect(status().isBadRequest());    					   
    }    
}
