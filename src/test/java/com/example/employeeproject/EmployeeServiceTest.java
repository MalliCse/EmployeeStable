package com.example.employeeproject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mockStatic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

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

	Employee emp = new Employee(1,"Malli","malli@gmail.com","7995157152",new BigDecimal(19000.00));
	// Test Case for Delete Details
	@Test
	public void deleteDetailsViaEmployeeServiceTest() throws NoSuchElementException {
		Mockito.when(emprepo.findById(emp.getId())).thenReturn(Optional.of(emp));
		empservice.deleteDetailsViaEmployeeService(emp.getId());
	}
	
	public void deleteDetailsViaEmployeeServiceTestFailure() throws NoSuchElementException {
		Mockito.when(emprepo.findById(emp.getId())).thenThrow(ResponseStatusException.class);
		assertThrows(ResponseStatusException.class,()->{empservice.deleteDetailsViaEmployeeService(emp.getId());});
	}
	
	/*
	 * @Test public void deleteDetailsViaEmployeeServiceFail() throws
	 * NoSuchElementException {
	 * Mockito.when(emprepo.findById(emp.getId())).thenReturn(Optional.empty());
	 * //Mockito.when(emprepo.deleteById(emp.getId())).thenReturn(Optional.empty());
	 * doNothing().when(emprepo).deleteById(emp.getId());
	 * //empservice.deleteDetailsViaEmployeeService(emp.getId());
	 * assertThat(empservice.deleteDetailsViaEmployeeService(emp.getId())).isEqualTo
	 * (ResponseEntity<String>()); }
	 */

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
		//empservice.saveDetailsViaEmployeeService(emp);
		assertEquals(empservice.saveDetailsViaEmployeeService(emp), "Details Are Saved Successfully");
	}
	
	@Test
	public void saveDetailsViaEmployeeServiceFailureTest() {
		//Mockito.when(emprepo.save(emp)).thenReturn(emp); 
		//doNothing().when(emprepo).save(emp);
		//empservice.saveDetailsViaEmployeeService(emp);
		Mockito.when(emprepo.findByNameAndEmail(emp.getName(), emp.getEmail())).thenReturn(emp);
		assertEquals(empservice.saveDetailsViaEmployeeService(emp), "Details Are Already Available");
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
	@Disabled
	public void sampleStaticMethodTest()
	{
		//assertEquals("Malli", EmployeeService.sampleStaticMethod("Malli"));
	}
	
	@Test
	public void saveAllDetailsViaEmployeeServiceTest()
	{
		//emprepo.saveAll(null)
		Employee emp1 = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Employee emp2 = new Employee(2, "Alekhya","kumar@gmail.com", "9949963521", new BigDecimal(5000.00));
		List<Employee> listOfEmployeesDetails=new ArrayList<Employee>();
		listOfEmployeesDetails.add(emp1);
		listOfEmployeesDetails.add(emp2);
		Mockito.when(emprepo.findByphonenumber(Mockito.anyString())).thenReturn(null);
		Mockito.when(emprepo.saveAll(listOfEmployeesDetails)).thenReturn(listOfEmployeesDetails);
		//doNothing().when(emprepo).saveAll(listOfEmployeesDetails);
		assertEquals(empservice.saveAllDetailsViaEmployeeService(listOfEmployeesDetails).size(), 0);
	}
	
	@Test
	public void saveAllDetailsViaEmployeeServiceTestFailure()
	{
		//emprepo.saveAll(null)
		Employee emp1 = new Employee(1, "Malli", "malli@gmail.com", "7995157152", new BigDecimal(19000.00));
		Employee emp2 = new Employee(2, "Alekhya","kumar@gmail.com", "9949963521", new BigDecimal(5000.00));
		List<Employee> listOfEmployeesDetails=new ArrayList<Employee>();
		listOfEmployeesDetails.add(emp1);
		listOfEmployeesDetails.add(emp2);
		Mockito.when(emprepo.findByphonenumber(Mockito.anyString())).thenReturn(emp1);
		Mockito.when(emprepo.saveAll(listOfEmployeesDetails)).thenReturn(listOfEmployeesDetails);
		//doNothing().when(emprepo).saveAll(listOfEmployeesDetails);
		assertEquals(empservice.saveAllDetailsViaEmployeeService(listOfEmployeesDetails).size(), 2);
	}
	// Private Method Junit
	
	@Test
	void testPrivateMethodTest() throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
	{
		Class[] parameterType = null;
		 Method method=EmployeeService.class.getDeclaredMethod("testPrivateMethod", parameterType);
		 
		 method.setAccessible(true);
		 String returnValue = (String)method.invoke(empservice, null);

			assertEquals(returnValue, "Testing Void Methiod");
	}
	
	// static method junit
	@Test
	void testStaticMethodTest()
	{
		//assertEquals(EmployeeService.testStaticMethod(), "Testing Static Method");
		
		
		Mockito.when(EmployeeService.testStaticMethod()).thenReturn("Testing Static Method");
		assertEquals(empservice.testStaticMethod1(), "Testing Static Method");
		
		/*
		 * try (MockedStatic mockStatic = mockStatic(EmployeeService.class)) {
		 * 
		 * mockStatic.when(EmployeeService::testStaticMethod).
		 * thenReturn("Testing Static Method");
		 * 
		 * //Inside scope assertEquals("bar", empservice.testStaticMethod1()); }
		 */

	}
	
	
}
