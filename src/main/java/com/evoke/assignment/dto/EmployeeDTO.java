package com.evoke.assignment.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Component
public class EmployeeDTO {
	
	private Integer employeeId;
	private String employeeName;
	private String employeeEmail;
	private String employeePhone;
	private String createdBy;
	@JsonSerialize(as = Date.class)
	@JsonFormat(pattern="dd-MM-yyyy")
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

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeePhone=" + employeePhone + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + "]";
	}

}
