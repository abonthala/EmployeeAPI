package com.evoke.assignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "Employee")
public class Employee
{
	@Id
	@Column(name = "Id")
	private Integer employeeId;
	
	@Column(name = "Name")
	private String employeeName;
	
	@Column(name = "Email")
	private String employeeEmail;
	
	@Column(name = "Phone")
	private String employeePhone;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdon")
	@JsonSerialize(as = Date.class)
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date createdOn;

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String employeeName, String employeeEmail, String employeePhone,
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
