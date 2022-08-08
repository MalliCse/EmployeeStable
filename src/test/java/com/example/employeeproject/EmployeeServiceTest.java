package com.example.employeeproject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.repository.EmployeeRepository;
import com.example.employeeproject.service.EmployeeService;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

	@Test
	@Disabled
	void test() {
		fail("Not yet implemented");
	}

	@InjectMocks
	EmployeeService empservice;
	@Mock
	EmployeeRepository emprepo;

	Employee emp = new Employee( "Malli", "malli@gmail.com", 123456, "mkurmala", "07-july");

	// Test Case for Delete Details
	@Test
	public void deleteDetailsViaEmployeeService() throws NoSuchElementException {
		Mockito.when(emprepo.findById(emp.getId())).thenReturn(Optional.of(emp));
		empservice.deleteDetailsViaEmployeeService(emp.getId());

	}
	
	@Test
	public void deleteDetailsViaEmployeeServiceFail() throws NoSuchElementException {
		Mockito.when(emprepo.findById(emp.getId())).thenReturn(Optional.empty());
		empservice.deleteDetailsViaEmployeeService(emp.getId());

	}

	@Test
	public void getDetailsViaEmployeeService() throws NoSuchElementException {
		Mockito.when(emprepo.findById(1)).thenReturn(Optional.of(emp));
		assertEquals(empservice.getDetailsViaEmployeeService(1).getId(), emp.getId());
	}
	
	@Test
	public void getDetailsViaEmployeeServiceFailure() throws NoSuchElementException {
		Mockito.when(emprepo.findById(1)).thenReturn(Optional.empty());
		
			assertThrows(Exception.class,()->empservice.getDetailsViaEmployeeService(1).getId());
		
	}

	@Test
	public void saveDetailsViaEmployeeServiceTest() {
		Mockito.when(emprepo.save(emp)).thenReturn(emp); 
		//doNothing().when(emprepo).save(emp);
		empservice.saveDetailsViaEmployeeService(emp);
	}
	 
	

	@Test
	public void getAllDetailsViaEmployeeServiceTest() {
		List<Employee> emplist = new ArrayList<Employee>();
		Page<Employee> emppages = new PageImpl<Employee>(emplist);
		Mockito.when(emprepo.findAll(PageRequest.of(1, 1))).thenReturn(emppages);

		assertEquals(empservice.getAllDetailsViaEmployeeService(1, 1), emppages);

	}

	@Test
	public void updateDetailsViaEmployeeServiceTest() {

		Mockito.when(emprepo.findById(1)).thenReturn(Optional.of(emp));
		Mockito.when(emprepo.save(emp)).thenReturn(emp);
		assertEquals("Employee Details Are Updated", empservice.updateDetailsViaEmployeeService(1, emp));

	}
	
	@Test
	public void sampleStaticMethodTest()
	{
		assertEquals("Malli", EmployeeService.sampleStaticMethod("Malli"));
	}
}
