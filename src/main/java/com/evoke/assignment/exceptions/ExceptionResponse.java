package com.evoke.assignment.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	
	private String errorMessage;
	private HttpStatus errorCode;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public HttpStatus getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
