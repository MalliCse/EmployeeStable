package com.example.employeeproject.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import com.example.employeeproject.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username = "Malli",password = "Malli@12345",roles = "USER")
public class EmployeeControllerTest {
	
	@Autowired
	TestRestTemplate testresttemplate;
	
	@LocalServerPort
	int port;
	
	Employee emp = new Employee( "Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
	
	@Test
	@Order(1)
	public void saveEmployeeDetailsTest()
	{
		HttpEntity<Object> requestentity=new HttpEntity<Object>(emp);
		ResponseEntity<String> response=testresttemplate.withBasicAuth("Malli", "Malli@12345").postForEntity("http://localhost:"+port+"/api/v1/saveEmployeeDetails", emp, String.class);
	    assertEquals("Employee Details Saved", response.getBody());
	}
	
	@Test
	@Order(2)
	public void getEmployeeDetailsTest()
	{
		//HttpEntity<Object> requestentity=new HttpEntity<Object>(emp);
		ResponseEntity<Employee> response=testresttemplate.withBasicAuth("Malli", "Malli@12345").getForEntity("http://localhost:"+port+"/api/v1/getEmployeeDetails/{empid}", Employee.class, 1);
	    assertEquals(1, response.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void getEmployeeDetailsTestFailure()
	{
		//HttpEntity<Object> requestentity=new HttpEntity<Object>(emp);
		ResponseEntity<String> response=testresttemplate.withBasicAuth("Malli", "Malli@12345").getForEntity("http://localhost:"+port+"/api/v1/getEmployeeDetails/{empid}", String.class, 2);
	    assertEquals("Employee Id Is Not Found", response.getBody());
	   // assertThrows(, ()->response.getBody().getId());
	}
	
	@Test
	@Order(4)
	public void updateEmployeeDetailsTest()
	{
		Employee updatedemp=new Employee( "Vishnu", "malli@gmail.com", 123456, "mkurmala", "07-july");
		HttpEntity<Object> requestentity=new HttpEntity<Object>(updatedemp);
		ResponseEntity<String> response=testresttemplate.withBasicAuth("Malli", "Malli@12345").exchange("http://localhost:"+port+"/api/v1/updateEmployeeDetails/{empid}", HttpMethod.PUT, requestentity, String.class, 1);
	    assertEquals("Employee Details Are Updated", response.getBody());
	}
	
	@Test
	@Order(5)
	public void DeleteEmployeeDetailsTest()
	{
		Employee updatedemp=new Employee("Vishnu", "malli@gmail.com", 123456, "mkurmala", "07-july");
		HttpEntity<Object> requestentity=new HttpEntity<Object>(updatedemp);
		ResponseEntity<String> response=testresttemplate.withBasicAuth("Malli", "Malli@12345").exchange("http://localhost:"+port+"/api/v1/deleteEmployeeDetails/{empid}", HttpMethod.DELETE, requestentity, String.class, 1);
	    assertEquals("Data Is deleted", response.getBody());
	}
	
	
	
	
	

}
