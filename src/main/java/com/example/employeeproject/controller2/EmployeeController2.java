package com.example.employeeproject.controller2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController2 {

	@GetMapping("/status")
	public String checkOrderStatus()
	{
		return "Order Is Booked";
	}
	
	
	
}
