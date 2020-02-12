package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Department;
import com.sample.service.EmployeeMetaDataService;

@RestController
public class EmployeeMetaDataController {

	
	@Autowired
	private EmployeeMetaDataService emplyeeMetaDataService;
	
	@RequestMapping(value = "/departments",method= RequestMethod.GET)
	public ResponseEntity<List<Department>> loadDepartmemts() {
		List<Department> departments = emplyeeMetaDataService.loadDepartments();
		return new ResponseEntity<>(departments,HttpStatus.OK);
	}
}
