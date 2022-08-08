package com.example.employeeproject.exceptions;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException() {
		log.error("No Details Found For Requested Employee ID");

		return new ResponseEntity<String>("Employee Id Is Not Found", HttpStatus.NOT_FOUND);
	}

}
