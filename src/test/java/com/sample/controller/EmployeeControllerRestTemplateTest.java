package com.sample.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;


public class EmployeeControllerRestTemplateTest {
	
	private RestTemplate restTemplate = null;
	
	public EmployeeControllerRestTemplateTest() {
		this.restTemplate = new RestTemplate();
	}
	
	@Test
	public void getEmployeeByIdTest() {
		String jwt = restTemplate.getForObject("http://localhost:8080/employeeApi/authenticate/", String.class);
		System.out.println(jwt);
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
	}

}
