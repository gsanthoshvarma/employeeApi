package com.sample.service;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.hr.dao.EmployeeDaoImpl;
import com.sample.hr.po.EmployeePO;
import com.sample.mapper.EmployeeMapper;
import com.sample.model.Address;
import com.sample.model.Employee;

@Repository
public class EmployeeRepository {
	
	private static final Logger LOG = Logger.getLogger(EmployeeRepository.class);
	
	@Autowired
	private EmployeeDaoImpl employeeDao;
	
	static List<Employee> employeeRepository = new ArrayList<Employee>();
	static {
		employeeRepository.add(new Employee(100, "Joke", "Roy", "HHH","12/12/1995", "Joke@gmail.com","http://localhost:8080/image1",new Address("101,Street ABC Town", "ABC", "ABC", "ABC", "100120")));
		employeeRepository.add(new Employee(101, "Kims", "Joke", "HHH","25/03/1995", "kims@gmail.com","http://localhost:8080/image2",new Address("102,Street ABC Town", "ABC", "ABC", "ABC", "100120")));
		employeeRepository.add(new Employee(102, "Vimi", "Somy", "HHH","10/01/1989", "vimi@gmail.com","http://localhost:8080/image3",new Address("103,Street ABC Town", "ABC", "ABC", "ABC", "100120")));
		employeeRepository.add(new Employee(103, "Aio", "Jio", "HHH","01/06/1998", "Aio@gmail.com","http://localhost:8080/image4",new Address("104,Street ABC Town", "ABC", "ABC", "ABC", "100120")));
		employeeRepository.add(new Employee(104, "Eio", "Vio", "HHH","09/11/1895", "Eio@gmail.com","http://localhost:8080/image5",new Address("105,Street ABC Town", "ABC", "ABC", "ABC", "100120")));
	}
	
	public List<Employee> getEmployees() {
		List<EmployeePO> employeePOs = employeeDao.getEmployees();
		LOG.info("Total number of Employees {} "+employeePOs.stream().count());
		List<Employee> employees = new ArrayList<>();
		employeePOs.parallelStream().forEach(e -> employees.add(EmployeeMapper.INSTANCE.employeePOToEmployee(e)));
		return employees.parallelStream().sorted(comparingLong(Employee::getId)).collect(toList());
		//
	}
	
	
	public Optional<Employee> getEmployee(int id) {
		EmployeePO employeePO = employeeDao.getEmployeeById(id);
		return Optional.of(EmployeeMapper.INSTANCE.employeePOToEmployee(employeePO));
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
