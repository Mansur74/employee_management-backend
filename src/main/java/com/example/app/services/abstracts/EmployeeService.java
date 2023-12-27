package com.example.app.services.abstracts;

import java.util.List;

import com.example.app.dtos.EmployeeDto;
import com.example.app.results.DataResult;

public interface EmployeeService {
	public DataResult<List<EmployeeDto>> getAllEmployees();
	public DataResult<EmployeeDto> getEmployeeById(int employeeId);
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto, int userId);
}
