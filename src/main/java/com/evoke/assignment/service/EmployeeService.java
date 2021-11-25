package com.evoke.assignment.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoke.assignment.exceptions.EmployeeAlreadyExistsException;
import com.evoke.assignment.exceptions.EmployeeNotFoundException;
import com.evoke.assignment.model.Employee;
import com.evoke.assignment.model.EmployeeUpdate;
import com.evoke.assignment.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
	
	public List<Employee> getAllEmployees()
	{
		logger.info("Inside getAllEmployees method of EmployeeService");
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Integer id)
	{
		logger.info("Inside getEmployeeById method of EmployeeService");
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(!(employee.isPresent()))
		{
			throw new EmployeeNotFoundException("No employee exists with Id: "+id);
		}
		return employee.get();
	}
	
	public Employee createEmployee(Employee employee)
	{
		logger.info("Inside createEmployee method of EmployeeService.");
		if(employeeRepository.findById(employee.getEmployeeId()).isPresent())
		{
			throw new EmployeeAlreadyExistsException("Employee with "+employee.getEmployeeId()+" already exists in database.");
		}
		return employeeRepository.save(employee);
	}
	
	public Employee removeEmployee(Integer id)
	{
		logger.info("Inside removeEmployee method of EmployeeService.");
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(!(employee.isPresent()))
		{
			throw new EmployeeNotFoundException("No employee exists with Id: "+id);
		}
		
		Employee tempEmployee = employee.get();
		employeeRepository.delete(employee.get());
		return tempEmployee;	
	}
	
	public Employee updateEmployee(Integer Id, EmployeeUpdate empUpdate)
	{
		logger.info("Inside updateEmployee method of EmployeeService.");
		Optional<Employee> employee = employeeRepository.findById(Id);
		
		if(!(employee.isPresent()))
		{
			throw new EmployeeNotFoundException("No employee exists with Id: "+Id);
		}
		
		Employee updatedEmployee = employee.get();
		updatedEmployee.setEmployeeName(empUpdate.getName());
		updatedEmployee.setEmployeeEmail(empUpdate.getEmail());
		updatedEmployee.setEmployeePhone(empUpdate.getPhone());
		return employeeRepository.save(updatedEmployee);
	}

}
