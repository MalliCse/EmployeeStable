package com.example.employeeproject.exceptions;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ErrorResponse {
	
	//General error message about nature of error
	  private String message;
	 
	  //Specific errors in API request processing
	  private List<String> details;
	
	  
	public ErrorResponse() {
		super();
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<String> getDetails() {
		return details;
	}


	public void setDetails(List<String> details) {
		this.details = details;
	}


	public ErrorResponse(String message, List<String> details) {
	    super();
	    this.message = message;
	    this.details = details;
	  }
	 
	  
	 

}
