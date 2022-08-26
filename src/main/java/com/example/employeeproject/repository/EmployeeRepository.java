package com.example.employeeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employeeproject.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	Employee findByphonenumber(@Param("phonenumber") String phonenumber);
	
	Employee findByNameAndEmail(@Param("name") String name,@Param("email") String email);
}
