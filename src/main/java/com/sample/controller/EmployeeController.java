package com.sample.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sample.exception.EmployeeNotFoundException;
import com.sample.model.Employee;
import com.sample.service.EmployeeRepository;
/**
 * 
 * @author Lasya
 *
 */
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
public class EmployeeController {
	private static final Logger LOG = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@RequestMapping(value="/employees",method=RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Employee> getEmployeeDetails() {
		LOG.info("EmployeeController - employees service got invoked");
		List<Employee> employees;
		if(LOG.isDebugEnabled()) {
			LOG.debug("End Point --> /employees");
		}
	    employees =  employeeRepository.getEmployees();
	    HttpHeaders headers = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(employees.parallelStream().count()).toUri();
		LOG.info(uri.toString());
		return employees;
	}
	
	@RequestMapping(value="/employees/{id}",method = RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		Employee emp = employeeRepository.getEmployee(id);
		if(emp == null) {
			LOG.error("Employee with id \"+id+ \" not found");
			throw new EmployeeNotFoundException("Employee with id "+id+ " not found");
		}
		
		/*WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEmployeeDetails());
		EntityModel<Employee> entityModel = new EntityModel<Employee>(emp);
		entityModel.add(linkTo.withRel("all-employee"));*/
		/*EntityModel<Employee> entityModel = new EntityModel<Employee>(emp);
		entityModel.add(new Link("").withRel(LinkRelation.of("employees")));*/
		return new ResponseEntity<>(emp,HttpStatus.OK);
		//return entityModel;
	}
	
	@RequestMapping(value="/employees/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> getEmployeeDetails(@PathVariable int id) {
		employeeRepository.deleteEmployee(id);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value="/employees",method=RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> saveEmployee(@Valid @RequestBody Employee employee) {
		employeeRepository.addEmployee(employee);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/employees",method=RequestMethod.PUT,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
		employeeRepository.updateEmployee(employee);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
