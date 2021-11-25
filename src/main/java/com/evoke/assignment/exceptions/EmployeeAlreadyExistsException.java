package com.evoke.assignment.exceptions;

import java.lang.runtime.ObjectMethods;

public class EmployeeAlreadyExistsException extends RuntimeException {

	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}

}
