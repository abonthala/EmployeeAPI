package com.evoke.assignment.response;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	
	private String message;
	private HttpStatus status;
	
	public ResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseModel(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ResponseModel [message=" + message + ", status=" + status + "]";
	}

}
