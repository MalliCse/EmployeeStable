package com.example.employeeproject.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class saveAllResponse {
	

	List<String> duplicatedContactNumbers;
	String message;
	
	public saveAllResponse(List<String> duplicatedContactNumbers, String message) {
		super();
		this.duplicatedContactNumbers = duplicatedContactNumbers;
		this.message = message;
	}

	public saveAllResponse() {
		super();
	}

	public List<String> getDuplicatedContactNumbers() {
		return duplicatedContactNumbers;
	}

	public void setDuplicatedContactNumbers(List<String> duplicatedContactNumbers) {
		this.duplicatedContactNumbers = duplicatedContactNumbers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
