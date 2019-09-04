package com.sample.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.dao.EmployeeDaoImpl;
import com.sample.model.Address;
import com.sample.model.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private EmployeeDaoImpl employeeDaoImpl;
	static List<Employee> employeeRepository = new ArrayList<Employee>();
	static {
		
		employeeRepository.add(new Employee(100, "Joke", "Roy", "HHH","12/12/1995", "Joke@gmail.com","http://localhost:8080/image1",new Address("101,Street ABC Town", "ABC", "ABC", "ABC", 100120)));
		employeeRepository.add(new Employee(101, "Kims", "Joke", "HHH","25/03/1995", "kims@gmail.com","http://localhost:8080/image2",new Address("102,Street ABC Town", "ABC", "ABC", "ABC", 100120)));
		employeeRepository.add(new Employee(102, "Vimi", "Somy", "HHH","10/01/1989", "vimi@gmail.com","http://localhost:8080/image3",new Address("103,Street ABC Town", "ABC", "ABC", "ABC", 100120)));
		employeeRepository.add(new Employee(103, "Aio", "Jio", "HHH","01/06/1998", "Aio@gmail.com","http://localhost:8080/image4",new Address("104,Street ABC Town", "ABC", "ABC", "ABC", 100120)));
		employeeRepository.add(new Employee(104, "Eio", "Vio", "HHH","09/11/1895", "Eio@gmail.com","http://localhost:8080/image5",new Address("105,Street ABC Town", "ABC", "ABC", "ABC", 100120)));
	}
	
	public List<Employee> getEmployees() {
		employeeDaoImpl.save();
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
