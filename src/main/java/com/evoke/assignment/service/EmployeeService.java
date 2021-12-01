package com.evoke.assignment.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.evoke.assignment.dto.EmployeeDTO;
import com.evoke.assignment.dto.EmployeeUpdateDTO;
import com.evoke.assignment.entity.Employee;
import com.evoke.assignment.exceptions.EmployeeAlreadyExistsException;
import com.evoke.assignment.exceptions.EmployeeNotFoundException;
import com.evoke.assignment.repository.EmployeeRepository;
import com.evoke.assignment.response.ResponseModel;

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
	
	public void createEmployee(EmployeeDTO employeeDTO)
	{
		logger.info("Inside createEmployee method of EmployeeService.");
		if(employeeRepository.findById(employeeDTO.getEmployeeId()).isPresent())
		{
			throw new EmployeeAlreadyExistsException("Employee with "+employeeDTO.getEmployeeId()+" already exists in database.");
		}
		Employee employee = DTOtoEntity(employeeDTO);
		employeeRepository.save(employee);
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
	
	public void updateEmployee(Integer Id, EmployeeUpdateDTO empUpdate)
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
		employeeRepository.save(updatedEmployee);
	}
	
	public Employee DTOtoEntity(EmployeeDTO employeeDTO)
	{
		return new Employee(employeeDTO.getEmployeeId(), employeeDTO.getEmployeeName(), employeeDTO.getEmployeeEmail(),
							employeeDTO.getEmployeePhone(), employeeDTO.getCreatedBy(), employeeDTO.getCreatedOn());
	}
	
	public ResponseModel getResponse(String message, HttpStatus status)
	{
		ResponseModel response = new ResponseModel();
		response.setMessage(message);
		response.setStatus(status);
		return response;
	}
}
