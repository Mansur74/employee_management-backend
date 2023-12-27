package com.example.app.mappers;

import com.example.app.dtos.EmployeeDto;
import com.example.app.models.Employee;

public class EmployeeMapper {
	public static Employee mapToEmployee(EmployeeDto employeeDto)
	{
		return Employee.builder()
				.firstName(employeeDto.getFirstName())
				.lastName(employeeDto.getLastName())
				.department(employeeDto.getDepartment())
				.salary(employeeDto.getSalary())
				.description(employeeDto.getDescription())
				.imgURL(employeeDto.getImgURL())
				.build();
	}
	
	public static EmployeeDto mapToEmployeeDto(Employee employee)
	{
		return EmployeeDto.builder()
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.department(employee.getDepartment())
				.salary(employee.getSalary())
				.description(employee.getDescription())
				.imgURL(employee.getImgURL())
				.build();
	}
}
