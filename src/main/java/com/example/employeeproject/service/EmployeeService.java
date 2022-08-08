package com.example.employeeproject.service;

import java.util.NoSuchElementException;
import java.util.Optional;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger log=LogManager.getLogger(EmployeeService.class);
	
	@Autowired
	EmployeeRepository emprepo;
	
	

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDetailsViaEmployeeService(Employee emp) {
		
		Employee emp1 =emprepo.save(emp);
		log.info("Employee Details Are Saved");
	}
	
	@Transactional(readOnly = true)
	public Employee getDetailsViaEmployeeService(int empid)  {
		try {
		Optional<Employee> emp=emprepo.findById(empid);
		log.info("Details Are Found For Requested Employee ID: {} and Request forwarded to controller",empid);
		if(emp.isPresent())
		return emp.get();
		else
		{
			throw new NoSuchElementException();
		}
	}
		finally {
			
		}
		
		
		
	}

	@Transactional(readOnly = true,propagation = Propagation.NEVER)
	public Page<Employee> getAllDetailsViaEmployeeService(int offset, int pagesize) {
		// TODO Auto-generated method stub
		return emprepo.findAll(PageRequest.of(offset, pagesize));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String updateDetailsViaEmployeeService(int empid, Employee emp) {
		// TODO Auto-generated method stub
		Optional<Employee> emp1=emprepo.findById(empid);
		Employee empobjectindb = emp1.get();
		log.info("Details Are Found For Requested Employee ID: {} for updating the details",empid);
			empobjectindb.setName(emp.getName());
		empobjectindb.setEmail(emp.getEmail());
		empobjectindb.setPhonenumber(emp.getPhonenumber());
		empobjectindb.setCreatedby(emp.getCreatedby());
		empobjectindb.setCreated_on(emp.getCreated_on());
		Employee studentdata =emprepo.save(empobjectindb);
		log.info("Employee Details Are Updated and Employee Id is {}",empid);
		return "Employee Details Are Updated";
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<String> deleteDetailsViaEmployeeService(int empid) {
			Optional<Employee> empobjectindb = emprepo.findById(empid);
			log.info("Details Are Found For Requested Employee ID: {} for deleting the details",empid);
			if(empobjectindb.isPresent())
			{
				System.out.println("Else Part");
				emprepo.deleteById(empid);
				log.info("Employee Details Are Deleted and Employee Id is {}",empid);
			}
			else
			{
				
				System.out.println("Else Part");
				return new ResponseEntity<String>("Data Is Not Found", HttpStatus.NOT_FOUND);
				
			}
		
		return new ResponseEntity<String>("Data Is deleted", HttpStatus.ACCEPTED);
		
		
		
	}
		
	 public static String sampleStaticMethod(String name)
	 {
		return name; 
	 }
	 
	
	 
	}

