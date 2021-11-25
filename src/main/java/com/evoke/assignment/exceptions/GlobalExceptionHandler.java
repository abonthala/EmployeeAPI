package com.evoke.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(HttpStatus.NOT_FOUND);
		response.setErrorMessage(employeeNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException employeeAlreadyExistsException)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(HttpStatus.CONFLICT);
		response.setErrorMessage(employeeAlreadyExistsException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

}
