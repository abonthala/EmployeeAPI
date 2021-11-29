package com.evoke.assignment.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.assignment.dto.EmployeeDTO;
import com.evoke.assignment.dto.EmployeeUpdateDTO;
import com.evoke.assignment.entity.Employee;
import com.evoke.assignment.exceptions.EmployeeNotFoundException;
import com.evoke.assignment.response.ResponseModel;
import com.evoke.assignment.service.EmployeeService;

@RestController
@RequestMapping("/employee-service")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
	/**
	 * Retrieves the list of all the employees 
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		logger.info("Inside getAllEmployees method of EmployeeController.");
		return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
	}
	
	/**
	 * Retrieve an employee by employeeId.
	 * @param empId
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer empId) throws EmployeeNotFoundException
	{
		logger.info("Inside getEmployee method of EmployeeController.");
		return new ResponseEntity<Employee>(empService.getEmployeeById(empId), HttpStatus.OK);
	}
	
	/**
	 * Inserts a new employee to the database.
	 * @param employee
	 */
	@PostMapping("/employees")
	public ResponseEntity<ResponseModel> addEmployee(@RequestBody EmployeeDTO employeeDTO)
	{
		logger.info("Inside addEmployee method of EmployeeController.");
		empService.createEmployee(employeeDTO);
		return new ResponseEntity<ResponseModel>(empService.getResponse("User is inserted successfully to DB", HttpStatus.CREATED), HttpStatus.CREATED);
	}
	
	/**
	 * Deletes an employee on the database which has the passed in employeeId.
	 * @param id
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException
	{
		logger.info("Inside deleteEmployee method of EmployeeController.");
		return new ResponseEntity<Employee>(empService.removeEmployee(id), HttpStatus.OK);
	}
	
	/**
	 * updates the details of the employee entity on the database.
	 * @param id
	 * @param empUpdate
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<ResponseModel> updateEmployee(@PathVariable("id") Integer id, 
											@RequestBody EmployeeUpdateDTO empUpdate) throws EmployeeNotFoundException
	{
		logger.info("Inside updateEmployee method of EmployeeController.");
		empService.updateEmployee(id, empUpdate);
		return new ResponseEntity<>(empService.getResponse("User is successfully updated in DB", HttpStatus.OK), HttpStatus.OK);
	}

}
