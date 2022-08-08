package com.example.employeeproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.employeeproject.controller.EmployeeController;
import com.example.employeeproject.model.Employee;
import com.example.employeeproject.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class)
@WithMockUser(username = "Malli",password = "Malli@12345",roles = "USER")
@ActiveProfiles("dev")
class EmployeeControllerTest {

	@Autowired
	MockMvc mockmvc;
	@MockBean
	EmployeeService empservice;
	
	@Test
	public void saveEmployeeDetailsTest() throws Exception
	{
		
		Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
		ObjectMapper objmap=new ObjectMapper();
		String empcontent=objmap.writeValueAsString(emp);
		//Mockito.when(empservice.saveDetailsViaEmployeeService(Mockito.any(Employee.class)))
		doNothing().when(empservice).saveDetailsViaEmployeeService(Mockito.any(Employee.class));
		
		RequestBuilder reqbuilder=MockMvcRequestBuilders.post("/api/v1/saveEmployeeDetails").contentType(MediaType.APPLICATION_JSON_VALUE).content(empcontent).accept(MediaType.APPLICATION_JSON_VALUE);
		
		  MvcResult result=mockmvc.perform(reqbuilder).andExpect(status().isCreated()).andReturn();
		  String finalresult=result.getResponse().getContentAsString(); 
		  assertThat(finalresult).isEqualTo("Employee Details Saved");
	}
	
	@Test
	public void getEmployeeDetailsDev() throws Exception
	{
		Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
		Mockito.when(empservice.getDetailsViaEmployeeService(Mockito.anyInt())).thenReturn(emp);
		mockmvc.perform(get("/api/v1/getEmployeeDetails/0").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0));
		
		/*
		 * .andReturn().getResponse().getContentAsString(); result.get
		 */
	}
	
	@Test
	public void getEmployeeDetailsDevFailure() throws Exception
	{
		Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
		Mockito.when(empservice.getDetailsViaEmployeeService(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
		mockmvc.perform(get("/api/v1/getEmployeeDetails/10").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
		
		/*
		 * .andReturn().getResponse().getContentAsString(); result.get
		 */
	}

}
