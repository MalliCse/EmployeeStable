package com.example.employeeproject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeproject.configurationdetails.ShowAPi;
import com.example.employeeproject.model.Employee;
import com.example.employeeproject.model.saveAllResponse;
import com.example.employeeproject.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController()
@RequestMapping("/api/v1")
@Api(value = "/api/v1", description = "Operations about Students")
@Validated
@ShowAPi
public class EmployeeController {
	
	@Autowired
	EmployeeService empservice; // Injecting Service class dependecy.
	
	Logger log=LogManager.getLogger(EmployeeController.class); // Log4j Object Creation
	
	@PostMapping(value="/saveEmployeeDetails",consumes  = {"application/json"})
	 @ApiOperation(value = "SaveDetails",response = String.class,tags="get")
	//@ApiIgnore
	public ResponseEntity<String> saveEmployeeDetails(@RequestBody @Valid Employee emp){
		log.info("Request is forwarded to Save Employee Details Service");
		return new ResponseEntity<String>(empservice.saveDetailsViaEmployeeService(emp),HttpStatus.CREATED);
		log.info("Response Is Sent");
	}
	
	@PostMapping(value="/saveAllEmployeeDetails",consumes  = {"application/json"})
	 @ApiOperation(value = "SaveAllDetails",response = String.class)
	@ResponseStatus(value = HttpStatus.CREATED)
	//@ShowAPi
	public ResponseEntity<Object> saveAllEmployeeDetails(@RequestBody @Valid List<Employee> emplist){
		
		log.info("Save All Student Details Api Request Is Received");
		
		List<String> duplicateEmployeeDetails =empservice.saveAllDetailsViaEmployeeService(emplist);
		saveAllResponse duplicatedEmployeeDetailsResponse=new saveAllResponse(duplicateEmployeeDetails, " Duplicate Details Are Found With Above Contacts");
		System.out.print(duplicatedEmployeeDetailsResponse.getMessage());
		if(duplicateEmployeeDetails.size()>0)
			return new ResponseEntity<Object>(duplicatedEmployeeDetailsResponse,HttpStatus.CREATED);
		
		return new ResponseEntity<Object>("All Records Are Persisted And No duplicate Records Are Found",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getEmployeeDetails/{empid}")
	public Employee getEmployeeDetailsDev(@PathVariable int empid) {
		
		log.info("Get Employee Details  Api Request Is Received in Dev Env");
		log.info("Request is forwarded to get Employee Details Service in Dev Env");
			
	return empservice.getDetailsViaEmployeeService(empid);

	}
	
	/*
	 * @GetMapping("/getEmployeeDetailsUAT/{empid}")
	 * 
	 * @Profile("uat") public Employee getEmployeeDetailsUat(@PathVariable int
	 * empid){
	 * 
	 * log.info("Get Employee Details  Api Request Is Received in UAT Env");
	 * log.info("Request is forwarded to get Employee Details Service in UAt Env");
	 * return empservice.getDetailsViaEmployeeService(empid); }
	 */
	
	/*
	 * @GetMapping("/getEmployeeDetailsLocal/{empid}")
	 * 
	 * @Profile("local") public Employee getEmployeeDetailsLocal(@PathVariable int
	 * empid){
	 * 
	 * log.info("Get Employee Details  Api Request Is Received in Local Environment"
	 * );
	 * log.info("Request is forwarded to get Employee Details Service In Local Env"
	 * ); return empservice.getDetailsViaEmployeeService(empid); }
	 */
	
	@GetMapping("/getAllEmployeeDetails/{offset}/{pagesize}")
	public Page<Employee> getAllEmployeeDetails(@PathVariable int offset,@PathVariable int pagesize){
		
		log.info("Get All Employee Details  Api Request Is Received");
		log.info("Request is forwarded to get All Employee Details Service");
		return empservice.getAllDetailsViaEmployeeService(offset,pagesize);
	}
	
	
	
	@PutMapping("/updateEmployeeDetails/{empid}")
	public ResponseEntity<String> updateEmployeeDetails(@PathVariable(required = true) int empid, @RequestBody @Valid Employee emp){
		
		log.info("Get All Employee Details  Api Request Is Received");
		if(empservice.updateDetailsViaEmployeeService(empid, emp).equals("Employee Details Are Not Found"))
			return new ResponseEntity<String>(empservice.updateDetailsViaEmployeeService(empid, emp),HttpStatus.NOT_FOUND);
		log.info("Request is forwarded to Update Employee Details Service");
		return new ResponseEntity<String>(empservice.updateDetailsViaEmployeeService(empid, emp),HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/deleteEmployeeDetails/{empid}")
	public ResponseEntity<String> deleteEmployeeDetails(@PathVariable(required = true) int empid)
	{
		log.info("Delete Employee Details  Api Request Is Received");
		if(empid<1)
			return new ResponseEntity<String>("Employee Id Should Be Positive Number",HttpStatus.BAD_REQUEST);
//		System.out.println(empservice.deleteDetailsViaEmployeeService(empid));
		log.info("Request Is Forwarded To Delete Employee Details Service ");
		return empservice.deleteDetailsViaEmployeeService(empid);
		
	}
	
	

}
