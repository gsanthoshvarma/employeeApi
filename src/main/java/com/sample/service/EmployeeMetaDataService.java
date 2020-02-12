package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sample.hr.dao.EmployeeMetaDateDao;
import com.sample.hr.po.DepartmentPO;
import com.sample.mapper.EmployeeMapper;
import com.sample.model.Department;

@Service
public class EmployeeMetaDataService {
	
	private static final Logger LOG = Logger.getLogger(EmployeeRepository.class);
	
	
	@Autowired
	@Qualifier("employeeMetaDataDaoImpl")
	private EmployeeMetaDateDao employeeMetaDataDao;
	
	public List<Department> loadDepartments(){
		LOG.info("called loadDepartment method");
		List<DepartmentPO> departmentPOs = employeeMetaDataDao.fetchDepartments();
		EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;
		List<Department> departments = new ArrayList<Department>();
		if(!departmentPOs.isEmpty()) {
			departmentPOs.forEach(departmentPO -> departments.add(employeeMapper.DepartmentPOToDepartment(departmentPO)));
		}
		return departments;
	}


	

}
