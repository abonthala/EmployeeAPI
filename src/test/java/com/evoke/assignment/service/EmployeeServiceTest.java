package com.evoke.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.evoke.assignment.dto.EmployeeDTO;
import com.evoke.assignment.dto.EmployeeUpdateDTO;
import com.evoke.assignment.entity.Employee;
import com.evoke.assignment.exceptions.EmployeeAlreadyExistsException;
import com.evoke.assignment.exceptions.EmployeeNotFoundException;
import com.evoke.assignment.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	
	@Mock
	EmployeeRepository employeeRepository;
	Employee employee;
	EmployeeDTO employeeDTO;
	List<Employee> employees = new ArrayList<>();
	
	@InjectMocks
	EmployeeService employeeService;
	
	@BeforeEach
	public void setUp()
	{
		employee = new Employee(1, "Test_Name", "Test_Email", "Test_Phone", "Test_CreatedBy", new Date());
		employeeDTO = new EmployeeDTO(1, "Test_Name", "Test_Email", "Test_Phone", "Test_CreatedBy", new Date());
		employees.add(employee);
	}
	
	@AfterEach
	public void tear()
	{
		employee = null;
		employees.removeAll(employees);
	}

	@Test
	void testGetAllEmployees() {
		when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> actual = employeeService.getAllEmployees();
		assertEquals(employees, actual);
	}

	@Test
	void testGetEmployeeById() {
		when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(employee));
		Employee actual = employeeService.getEmployeeById(1);
		assertEquals(employee, actual);
	}

	@Test
	void testCreateEmployee() {
		when(employeeRepository.save(Mockito.any())).thenReturn(employee);
		employeeService.createEmployee(employeeDTO);
		//assertEquals(actual, employee);
	}

	@Test
	void testRemoveEmployee() {
		when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		Employee actual = employeeService.removeEmployee(1);
		assertEquals(employee, actual);
	}

	@Test
	void testUpdateEmployee() {
		when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
		employeeService.updateEmployee(1, new EmployeeUpdateDTO("Test_Name","Test_Email", "Test_Phone"));
		//assertEquals(employee, actual);
	}
	
	@Test
	public void testGetEmployeeByIdEmployeeNotFoundException()
	{
		assertThrows(EmployeeNotFoundException.class, ()->employeeService.getEmployeeById(0));
	}
	
	@Test
	public void testCreateEmployeeEmployeeAlreadyExistsException()
	{
		when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.createEmployee(employeeDTO));
	}

}
