package com.example.app.business.abstracts;

import java.util.List;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.PageResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.EmployeeDto;

public interface EmployeeService {
	public DataResult<PageResult<EmployeeDto>> getAllEmployees(int pageNo, int pageSize);
	public DataResult<EmployeeDto> getEmployeeById(int employeeId);
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto);
	public DataResult<EmployeeDto> updateEmployeeById(EmployeeDto employeeDto, int employeeId);
	public Result deleteEmployee(int employeeId);
}
