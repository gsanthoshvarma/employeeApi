package com.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.sample.hr.po.EmployeePO;
import com.sample.model.Employee;

@Mapper
public interface EmployeeMapper {

	 EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	   
	    @Mappings({
	    	@Mapping(source = "employeeId", target = "id"),
	    	@Mapping(source = "phoneNumber", target = "mobileNumber"),
	    	@Mapping(source = "hireDate", target = "dateOfJoined"),
	    	@Mapping(source="departmentPO.location.locationId",target="address.id"),
	    	@Mapping(source="departmentPO.location.address",target="address.address"),
	    	@Mapping(source="departmentPO.location.city",target="address.city"),
	    	@Mapping(source="departmentPO.location.postalCode",target="address.pincode"),
	    	@Mapping(source="departmentPO.location.state",target="address.state"),
	    	@Mapping(source="departmentPO.location.country.countryName", target="address.country")
	    })
	         Employee employeePOToEmployee(EmployeePO employeePO);

}
