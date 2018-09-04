package com.criticalsoftware.springboot.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.criticalsoftware.springboot.exception.ErrorDetails;
import com.criticalsoftware.springboot.exception.OperationExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class OperationControllerMockMvcTest {
		
    private MockMvc mockMvc;

    @Mock
    private OperationService service;
    
    @InjectMocks
    private OperationController operationController;
    
    private JacksonTester<ErrorDetails> jsonErrorDetails;
    
    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(operationController)
                  .setControllerAdvice(new OperationExceptionHandler())                
                  .build();
    }
    
//	    @Test
//	    public void exceptionShouldRetrieveErrorResponse() throws Exception {
//	    	given(service.calculate(new OperationRequest())).willThrow(new Exception());
//	    	
//	    	MockHttpServletResponse response = mockMvc.perform(post("/calculate").accept(MediaType.APPLICATION_JSON))
//	    													   .andReturn().getResponse();
//	    	
//	    	//jsonErrorDetails.write(new ErrorDetails(new Date(), "Internal server error", "Generic exception test"));
//	    	assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//	    	assertThat(response.getContentAsString()).contains("Message: Operation not found");
//	    }
    
    @Test
    public void operationNotFoundExceptionShouldRetrieveErrorResponse() throws Exception {
    	given(service.calculate(new OperationRequest(10,20,"newOp"))).willThrow(new OperationNotFoundException("Unrecognized operation"));
    	
    	MockHttpServletResponse response = mockMvc.perform(post("/calculate").accept(MediaType.APPLICATION_JSON))
    													   .andReturn().getResponse();
    	
    	assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    	assertThat(response.getContentAsString()).contains("Message: Operation not found");
    }	
}
