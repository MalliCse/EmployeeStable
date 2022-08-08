package com.example.employeeproject.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.employeeproject.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Malli",password = "Malli@12345",roles = "USER")
@ActiveProfiles("dev")
class EmployeeControllerTestMockMvc {


	  @Autowired
	  private MockMvc mockmvc;


	  @Test
	  public void testsaveEmployeeDetails() throws Exception {

		  Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
			ObjectMapper objmap=new ObjectMapper();
			String empcontent=objmap.writeValueAsString(emp);
		  mockmvc.perform(post("/api/v1/saveEmployeeDetails").contentType(MediaType.APPLICATION_JSON_VALUE).content(empcontent).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }
	  
	  
	  @Test
	  public void testgetEmployeeDetailsDevSuccess() throws Exception {

		  //Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
		  mockmvc.perform(get("/api/v1//getEmployeeDetails/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }
	  
	  @Test
	  public void testgetEmployeeDetailsDevFailure() throws Exception {

		  //Employee emp = new Employee("Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");
		  mockmvc.perform(get("/api/v1//getEmployeeDetails/2")).andExpect(status().isNotFound());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }
	  
	  @Test
	  public void testupdateEmployeeDetails() throws Exception {

		  Employee emp1 = new Employee("Vishnu", "malli@gmail.com", 123456, "mkurmala", "07-july");
			ObjectMapper objmap=new ObjectMapper();
			String empcontent=objmap.writeValueAsString(emp1);
		  mockmvc.perform(put("/api/v1/updateEmployeeDetails/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(empcontent).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }
	  
	  
	  @Test
	  public void testupdateEmployeeDetailsFailure() throws Exception {

		  Employee emp1 = new Employee("Vishnu", "malli@gmail.com", 123456, "mkurmala", "07-july");
			ObjectMapper objmap=new ObjectMapper();
			String empcontent=objmap.writeValueAsString(emp1);
		  mockmvc.perform(put("/api/v1/updateEmployeeDetails/2").contentType(MediaType.APPLICATION_JSON_VALUE).content(empcontent).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }

	  @Test
	  public void testdeleteEmployeeDetailsFailure() throws Exception {

			/*
			 * Employee emp1 = new Employee("Vishnu", "malli@gmail.com", 123456, "mkurmala",
			 * "07-july"); ObjectMapper objmap=new ObjectMapper(); String
			 * empcontent=objmap.writeValueAsString(emp1);
			 */
		  mockmvc.perform(delete("/api/v1/deleteEmployeeDetails/-1")).andExpect(status().isBadRequest());
			
			 // MvcResult result=mockmvc.perform(reqbuilder).andReturn();
	  }
	  
}
