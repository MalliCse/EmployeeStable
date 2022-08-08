package com.example.employeeproject;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.repository.EmployeeRepository;




@SpringBootTest
class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository emprepo;
	
	@Test
	 void setup()
	{
		Employee testemp = new Employee();
		testemp.setName("malli");
		testemp.setEmail("mallikharjuna116@gmail.com");
		testemp.setCreated_on("08-04-2022");
		testemp.setCreatedby("mkurmala");
		testemp.setPhonenumber(79951571);
		emprepo.save(testemp);
	}
	
	@Test
	public void findByPhoneNumberTest()
	{
		Assertions.assertEquals(emprepo.findByphonenumber(79951571).getId(),1);
	}
	
	

}
