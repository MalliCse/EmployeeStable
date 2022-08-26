package com.example.employeeproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import org.springframework.web.server.ResponseStatusException;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger log=LogManager.getLogger(EmployeeService.class);
	
	@Autowired
	EmployeeRepository emprepo;

	@Transactional(propagation = Propagation.REQUIRED)
	public String saveDetailsViaEmployeeService(Employee emp) {
		Employee dbemp=emprepo.findByNameAndEmail(emp.getName(), emp.getEmail());
		if(dbemp==null)
		{
		Employee emp1 =emprepo.save(emp);
		log.info("Employee Details Are Saved");
		return "Details Are Saved Successfully";
		}
		
		return "Details Are Already Available";
		
	}
	
	@Transactional(readOnly = true)
	public Employee getDetailsViaEmployeeService(int empid)  {
		//return emprepo.findById(empid).orElseThrow(()->new NoSuchElementException());
		return emprepo.findById(empid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User id :"+empid+"Not Found"));
	}
	
	/*
	 * @Transactional(readOnly = true) public Employee
	 * getDetailsViaEmployeeService(String empid) { //return
	 * emprepo.findById(empid).orElseThrow(()->new NoSuchElementException()); return
	 * emprepo.findById(empid).orElseThrow(()->new
	 * ResponseStatusException(HttpStatus.NOT_FOUND,"User id :"+empid+"Not Found"));
	 * }
	 */


	@Transactional(readOnly = true,propagation = Propagation.NEVER)
	public Page<Employee> getAllDetailsViaEmployeeService(int offset, int pagesize) {
		// TODO Auto-generated method stub
		return emprepo.findAll(PageRequest.of(offset, pagesize));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String updateDetailsViaEmployeeService(int empid, Employee emp) {
		// TODO Auto-generated method stub
		Employee empobjectindb =emprepo.findById(empid).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"User id :"+empid+"Not Found"));
		log.info("Details Are Found For Requested Employee ID: {} for updating the details",empid);
		  empobjectindb.setName(emp.getName()); 
		  empobjectindb.setEmail(emp.getEmail());
		  empobjectindb.setPhonenumber(emp.getPhonenumber());
		  empobjectindb.setSalary(emp.getSalary());
		   Employee studentdata=emprepo.save(empobjectindb);
		  log.info("Employee Details Are Updated and Employee Id is {}",empid);
		return "Employee Details Are Updated";
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<String> deleteDetailsViaEmployeeService(int empid) {
			Employee empobjectindb = emprepo.findById(empid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User id :"+empid+"Not Found"));
			log.info("Details Are Found For Requested Employee ID: {} for deleting the details",empid);
			emprepo.deleteById(empid);
			return new ResponseEntity<String>("Requested Employee Details Are deleted",HttpStatus.OK);
		
	}

	public List<String> saveAllDetailsViaEmployeeService(List<Employee> emplist) {
		List<Employee> updatedEmployeeList=new ArrayList<Employee>();
		List<String> duplicaterecords=new ArrayList<String>();
		for(Employee emp:emplist)
		{
			Employee dbemp = emprepo.findByphonenumber(emp.getPhonenumber());
			if(dbemp!=null)
			{
				duplicaterecords.add(dbemp.getPhonenumber());
			}
			else
			{
				updatedEmployeeList.add(emp);
			}
		}
		
		emprepo.saveAll(updatedEmployeeList);
		return duplicaterecords;
	}
		
}

