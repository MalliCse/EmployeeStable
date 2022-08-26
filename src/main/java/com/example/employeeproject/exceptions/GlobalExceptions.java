package com.example.employeeproject.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

	Logger log = LogManager.getLogger(GlobalExceptions.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Request Body is Not received {}{}", ex.getMessage(), status);
		return new ResponseEntity<Object>("Request Body Is Not Received", status);
	}

	
	/*
	 * @ExceptionHandler(NoSuchElementException.class) public ResponseEntity<String>
	 * handleNoSuchElementException() {
	 * log.error("No Details Found For Requested Employee ID");
	 * 
	 * return new ResponseEntity<String>("Employee Id Is Not Found",
	 * HttpStatus.NOT_FOUND); }
	 */
	 

	@Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<String> details = new ArrayList<>();
	    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	      details.add(error.getDefaultMessage());
	    }
	    ErrorResponse error = new ErrorResponse("Validation Failed", details);
	    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintException(ConstraintViolationException ex,WebRequest request) {

		Set<ConstraintViolation<?>> violations=ex.getConstraintViolations();
		//ConstraintViolation<?> violation=violations.iterator().next();
		//violation.getProperties()
		Iterator<ConstraintViolation<?>> violation=violations.iterator();
		List<String> constraintfailuremessages=new ArrayList<String>();
		while(violation.hasNext())
		{
			constraintfailuremessages.add(violation.next().getMessage().toString());
		}
		//return new ResponseEntity(violation.getMessage(),HttpStatus.BAD_REQUEST)
		return new ResponseEntity(constraintfailuremessages.toString(),HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(DataIntegrityViolationException.class) 
	  public String handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex) { 
		return "Duplicates Records Are Received";
				
				//((ConstraintViolationException)ex.getCause()).getMessage();
		
		 
				}
	
		@ExceptionHandler(ResponseStatusException.class)
		public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
					
			GlobalErrorResponse result=new GlobalErrorResponse(ex.getReason(), ex.getStatus());
			return new ResponseEntity<Object>(result, ex.getStatus());
				}
		

				 

}
