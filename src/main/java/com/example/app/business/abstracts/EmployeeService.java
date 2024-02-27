package com.example.app.business.abstracts;

import java.util.List;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.PageResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.EmployeeDto;

public interface EmployeeService {
	public DataResult<PageResult<EmployeeDto>> getAllEmployees(int pageNo, int pageSize);
	public DataResult<PageResult<EmployeeDto>> getEmployeesByUserId(int pageNo, int pageSize, int userId);
	public DataResult<EmployeeDto> getEmployeeByIdAndUserId(int employeeId, int userId);
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto , int userId);
	public DataResult<EmployeeDto> updateEmployeeByIdAndUserId(EmployeeDto employeeDto, int employeeId, int userId);
	public Result deleteEmployeeByIdAndUserId(int employeeId, int userId);
}
