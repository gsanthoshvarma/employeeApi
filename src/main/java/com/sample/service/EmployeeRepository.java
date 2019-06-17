package com.sample.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sample.model.Employee;

@Repository
public class EmployeeRepository {
	static List<Employee> employeeRepository = new ArrayList<Employee>();
	static {
		employeeRepository.add(new Employee(100, "Joke", "Roy", "HHH","12/12/1995", "Joke@gmail.com"));
		employeeRepository.add(new Employee(101, "Kims", "Joke", "HHH","25/03/1995", "kims@gmail.com"));
		employeeRepository.add(new Employee(102, "Vimi", "Somy", "HHH","10/01/1989", "vimi@gmail.com"));
		employeeRepository.add(new Employee(103, "Aio", "Jio", "HHH","01/06/1998", "Aio@gmail.com"));
		employeeRepository.add(new Employee(104, "Eio", "Vio", "HHH","09/11/1895", "Eio@gmail.com"));
	}
	
	public List<Employee> getEmployees() {
		return employeeRepository.stream().sorted((e1,e2) -> new Long(e1.getId()).compareTo(new Long(e2.getId()))).collect(Collectors.toList());
	}
	
	public Employee getEmployee(int id) {
		Iterator<Employee> iterator = employeeRepository.iterator();
		while(iterator.hasNext()) {
			Employee employee = iterator.next();
			if(id == employee.getId()) {
				return employee;
			}
		}
		return null;
	}
	
	public void deleteEmployee(final int id) {
		Iterator<Employee> iterator = employeeRepository.iterator();
		 while(iterator.hasNext()) {
			 if(iterator.next().getId() == id) {
				 iterator.remove();
			 }
		 }
	}
	
	public void addEmployee(Employee employee){
		employeeRepository.add(employee); 
	}
	
	public void updateEmployee(Employee employee) {
		boolean isFound = false;
		Iterator<Employee> iterator = employeeRepository.iterator();
		 while(iterator.hasNext()) {
			 if(iterator.next().getId() == employee.getId()) {
				 isFound = true;
				 iterator.remove();
			 }
	}
		 if(isFound) {
			 employeeRepository.add(employee);
		 }
	}

}
