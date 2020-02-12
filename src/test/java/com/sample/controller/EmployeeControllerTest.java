package com.sample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sample.config.EmployeeConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= EmployeeConfig.class)
public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	@InjectMocks
	private EmployeeController employeeController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	public void getEmployeeDetailsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
		       .andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
}
