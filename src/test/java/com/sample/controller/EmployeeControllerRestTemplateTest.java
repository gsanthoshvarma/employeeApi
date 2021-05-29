package com.sample.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;


public class EmployeeControllerRestTemplateTest {
	
	private RestTemplate restTemplate = null;
	private static final String AUTHENTICATE_REQUEST = "http://localhost:8080/employeeApi/authenticate/";
	
	public EmployeeControllerRestTemplateTest() {
		this.restTemplate = new RestTemplate();
	}
	
	@Test
	public void getEmployeeByIdTest() throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		MultiValueMap<String,String> requestMap = new LinkedMultiValueMap<>();
			requestMap.add("username", "admin");
			requestMap.add("password", "admin");
			final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(requestMap ,
			        headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(AUTHENTICATE_REQUEST, entity, String.class);
			System.out.println(responseEntity.getBody());
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
	}

}
