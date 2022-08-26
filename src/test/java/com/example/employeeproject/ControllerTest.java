package com.example.employeeproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.employeeproject.controller.EmployeeController;
import com.example.employeeproject.model.Employee;
import com.example.employeeproject.model.saveAllResponse;
import com.example.employeeproject.service.EmployeeService;

@SpringBootTest
class ControllerTest {

	@InjectMocks
	EmployeeController empcontroller;
	@Mock
	EmployeeService empservice;
	
	@Test
	public void saveEmployeeDetailsTest()
	{
		Employee emp = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Mockito.when(empservice.saveDetailsViaEmployeeService(emp)).thenReturn("Details Are Saved Successfully");
		empcontroller.saveEmployeeDetails(emp);
	    assertEquals(empcontroller.saveEmployeeDetails(emp).getBody(), "Details Are Saved Successfully");
	    assertEquals(empcontroller.saveEmployeeDetails(emp).getStatusCodeValue(), 201);
	}
	
	@Test
	public void getEmployeeDetailsDevTest()
	{
		Employee emp = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Mockito.when(empservice.getDetailsViaEmployeeService(emp.getId())).thenReturn(emp);
		assertEquals(empcontroller.getEmployeeDetailsDev(emp.getId()), emp);
	}
	@Test
	public void deleteEmployeeDetailsTest()
	{
		int empid=1;
		ResponseEntity<String> r=new ResponseEntity<String>("Requested Employee Details Are deleted",HttpStatus.OK);
		Mockito.when(empservice.deleteDetailsViaEmployeeService(empid)).thenReturn(r);
		assertEquals(empcontroller.deleteEmployeeDetails(empid).getBody(), r.getBody());
	}
	
	@Test
	public void deleteEmployeeDetailsTestFailure()
	{
		int empid=-1;
		//ResponseEntity<String> r=new ResponseEntity<String>("Requested Employee Details Are deleted",HttpStatus.OK);
		//Mockito.when(empservice.deleteDetailsViaEmployeeService(empid)).thenReturn(r);
		assertEquals(empcontroller.deleteEmployeeDetails(empid).getBody(),"Employee Id Should Be Positive Number");
		assertEquals(empcontroller.deleteEmployeeDetails(empid).getStatusCodeValue(), 400);
	}
	
	@Test
	public void updateEmployeeDetailsTest()
	{
		Employee emp = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Mockito.when(empservice.updateDetailsViaEmployeeService(emp.getId(), emp)).thenReturn("Employee Details Are Updated");
		assertEquals(empcontroller.updateEmployeeDetails(emp.getId(), emp).getBody(), "Employee Details Are Updated");
		
	}
	
	@Test
	public void updateEmployeeDetailsTestFailure()
	{
		Employee emp = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Mockito.when(empservice.updateDetailsViaEmployeeService(emp.getId(), emp)).thenReturn("Employee Details Are Not Found");
		assertEquals(empcontroller.updateEmployeeDetails(emp.getId(), emp).getBody(), "Employee Details Are Not Found");
		
		
	}
	
	
	
	
	@Test
	public void saveAllEmployeeDetailsNoDuplicationTest()
	{
		Employee emp1 = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Employee emp2 = new Employee(2, "Alekhya","kumar@gmail.com", "9949963521", new BigDecimal(5000.00));
		List<Employee> listOfEmployeesDetails=new ArrayList<Employee>();
		Mockito.when(empservice.saveAllDetailsViaEmployeeService(listOfEmployeesDetails)).thenReturn(new ArrayList<>());
		ResponseEntity<Object>  r  =empcontroller.saveAllEmployeeDetails(listOfEmployeesDetails);
		assertEquals(r.getBody(), "All Records Are Persisted And No duplicate Records Are Found");
		assertEquals(r.getStatusCodeValue(), 201);
	}
	
	@Test
	public void saveAllEmployeeDetailsDuplicationTest()
	{
		Employee emp1 = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Employee emp2 = new Employee(2, "Alekhya","kumar@gmail.com", "9949963521", new BigDecimal(5000.00));
		List<Employee> listOfEmployeesDetails=new ArrayList<Employee>();
		listOfEmployeesDetails.add(emp1);
		listOfEmployeesDetails.add(emp2);
		List<String> duplicationDetails=new ArrayList<String>();
		duplicationDetails.add(listOfEmployeesDetails.get(0).getPhonenumber());
		Mockito.when(empservice.saveAllDetailsViaEmployeeService(listOfEmployeesDetails)).thenReturn(duplicationDetails);
		ResponseEntity<Object>  r  =empcontroller.saveAllEmployeeDetails(listOfEmployeesDetails);
		saveAllResponse duplicatedEmployeeDetailsResponse=(saveAllResponse) r.getBody();
		assertEquals(duplicatedEmployeeDetailsResponse.getMessage(), " Duplicate Details Are Found With Above Contacts");
		assertEquals(r.getStatusCodeValue(), 201);
		assertEquals(duplicatedEmployeeDetailsResponse.getDuplicatedContactNumbers(),duplicationDetails);
	}
	
	@Test
	public void getAllEmployeeDetailsTest()
	{
		Employee emp1 = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Employee emp2 = new Employee(2, "Alekhya","kumar@gmail.com", "9949963521", new BigDecimal(5000.00));
		List<Employee> listOfEmployeesDetails=new ArrayList<Employee>();
		final Page<Employee> page = new PageImpl<>(listOfEmployeesDetails);
		Mockito.when(empservice.getAllDetailsViaEmployeeService(Mockito.anyInt(), Mockito.anyInt())).thenReturn(page);
		assertEquals(empcontroller.getAllEmployeeDetails(0, 2), page);
	}

}
