package com.example.app.services.abstracts;

import java.util.List;

import com.example.app.dtos.EmployeeDto;
import com.example.app.results.DataResult;
import com.example.app.results.PageResult;
import com.example.app.results.Result;

public interface EmployeeService {
	public DataResult<PageResult<EmployeeDto>> getAllEmployees(int pageNo, int pageSize);
	public DataResult<EmployeeDto> getEmployeeById(int employeeId);
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto);
	public DataResult<EmployeeDto> updateEmployeeById(EmployeeDto employeeDto, int employeeId);
	public Result deleteEmployee(int employeeId);
}
