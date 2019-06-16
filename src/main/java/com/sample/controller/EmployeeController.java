package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Employee;
import com.sample.service.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value="/employees",method=RequestMethod.GET)
	public List<Employee> getEmployeeDetails() {
		return employeeRepository.getEmployees();
	}
	
	@RequestMapping(value="/employees/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> getEmployeeDetails(@PathVariable int id) {
		employeeRepository.deleteEmployee(id);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value="/employees/save/",method=RequestMethod.POST)
	public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
		employeeRepository.addEmployee(employee);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/employees/update/",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
		employeeRepository.updateEmployee(employee);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
