package com.evoke.assignment.dto;

import java.util.Date;

public class EmployeeDTO {
	
	private Integer employeeId;
	private String employeeName;
	private String employeeEmail;
	private String employeePhone;
	private String createdBy;
	private Date createdOn;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(Integer employeeId, String employeeName, String employeeEmail, String employeePhone,
			String createdBy, Date createdOn) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeePhone = employeePhone;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
