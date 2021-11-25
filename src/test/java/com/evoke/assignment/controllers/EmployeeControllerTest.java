package com.evoke.assignment.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.evoke.assignment.model.Employee;
import com.evoke.assignment.model.EmployeeUpdate;
import com.evoke.assignment.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	// creates the mock object of employeeService at runtime.
	@Mock
	private EmployeeService employeeService;
	private Employee employee;
	List<Employee> employeeList = new ArrayList<>();
	
	// Initialize the employeeController object.
	@InjectMocks
	EmployeeController employeeController;
	
	// Autowiring the MockMvc, through this we can send Mock HTTP requests to controller. 
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp()
	{
		employee = new Employee(1, "Test_Name", "Test_Email", "Test_Phone", "Test_CreatedBy", new Date());
		employeeList.add(employee);
		
		// Creating the MockMvc instance using StandaloneSetup by registering the controller instance.
		mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@AfterEach
	public void tearDown()
	{
		employee = null;
		employeeList.removeAll(employeeList);
	}

	@Test
	void testGetAllEmployees() throws Exception{
		when(employeeService.getAllEmployees()).thenReturn(employeeList);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee-service/employees").
						contentType(MediaType.APPLICATION_JSON).
						content(mapToJson(employeeList))).
				        andExpect(MockMvcResultMatchers.status().isOk()).
						andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, mapToJson(employeeList));
	}

	@Test
	void testGetEmployee() throws Exception{
		when(employeeService.getEmployeeById(Mockito.any(Integer.class))).thenReturn(employee);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee-service/employees/1").
											  contentType(MediaType.APPLICATION_JSON).
											  content(mapToJson(employee))).
				                              andExpect(MockMvcResultMatchers.status().isOk()).
											  andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(mvcResult.getResponse().getContentAsString(), mapToJson(employee));
	}

	@Test
	void testAddEmployee() throws Exception{
		when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/employee-service/employees").
				                              contentType(MediaType.APPLICATION_JSON).
				                              content(mapToJson(employee))).
				                              andExpect(MockMvcResultMatchers.status().isCreated()).
				                              andReturn();
		assertEquals(201, mvcResult.getResponse().getStatus());
		assertEquals(mvcResult.getResponse().getContentAsString(), mapToJson(employee));
	}

	@Test
	void testDeleteEmployee() throws Exception{
		when(employeeService.removeEmployee(Mockito.any(Integer.class))).thenReturn(employee);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/employee-service/employees/1").
							  contentType(MediaType.APPLICATION_JSON).
							  content(mapToJson(employee))).
				              andExpect(MockMvcResultMatchers.status().isOk()).
				              andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(mvcResult.getResponse().getContentAsString(), mapToJson(employee));
	}

	@Test
	void testUpdateEmployee() throws Exception{
		when(employeeService.updateEmployee(Mockito.any(Integer.class), Mockito.any(EmployeeUpdate.class))).thenReturn(employee);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/employee-service/employees/1").
				                              contentType(MediaType.APPLICATION_JSON).
				                              content(mapToJson(employee))).
				                              andExpect(MockMvcResultMatchers.status().isOk())
				                              .andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(mvcResult.getResponse().getContentAsString(), mapToJson(employee));
	}
	
	public static String mapToJson(final Object obj) throws JsonProcessingException{
            return new ObjectMapper().writeValueAsString(obj);
        }
}

