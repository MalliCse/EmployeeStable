package com.example.employeeproject;

import java.math.BigDecimal;
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
		testemp.setPhonenumber("7995157152");
		testemp.setSalary(new BigDecimal(1990.00));
		emprepo.save(testemp);
	}
	
	@Test
	public void findByPhoneNumberTest()
	{
		Assertions.assertEquals(emprepo.findByphonenumber("7995157152").getId(),1);
	}
	
	

}
