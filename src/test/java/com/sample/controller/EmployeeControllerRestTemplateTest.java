package com.sample.controller;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.model.UserValue;

import junit.framework.Assert;


public class EmployeeControllerRestTemplateTest {
	
	private RestTemplate restTemplate = null;
	private static final String USERNAME ="admin";
	private static final String PASSWORD = "admin";
	
	private static final String AUTHENTICATE_REQUEST = "http://localhost:8080/employeeApi/authenticate/";
	
	public EmployeeControllerRestTemplateTest() {
		this.restTemplate = new RestTemplate();
	}
	
	@Test
	public void jwtTest() throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		    UserValue userValue = new UserValue();
		    userValue.setUsername(USERNAME);
		    userValue.setPassword(PASSWORD);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(AUTHENTICATE_REQUEST, userValue, String.class);
			System.out.println(responseEntity.getBody());
	}
	
	@Test
	public void wrongUsernameTest() throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		    UserValue userValue = new UserValue();
		    userValue.setUsername(USERNAME+Integer.MIN_VALUE);
		    userValue.setPassword(PASSWORD);
		    try {
		    	ResponseEntity<String> responseEntity = restTemplate.postForEntity(AUTHENTICATE_REQUEST, userValue, String.class);
		    }catch (HttpClientErrorException e) {
		    	Assert.assertEquals(new Integer(403).intValue() , e.getStatusCode().value());
			}
	}

}
