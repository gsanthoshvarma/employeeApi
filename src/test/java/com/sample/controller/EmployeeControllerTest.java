package com.sample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.Assert.assertEquals;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"dispatcher-servlet.xml"})*/
public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Test
	public void getEmployeeDetailsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
		       .andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

}
