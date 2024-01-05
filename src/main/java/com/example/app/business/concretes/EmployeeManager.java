package com.example.app.business.concretes;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.app.business.abstracts.EmployeeService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.PageResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.core.utilities.results.SuccessResult;
import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.dtos.EmployeeDto;
import com.example.app.entities.Employee;

import static com.example.app.mappers.EmployeeMapper.mapToEmployee;
import static com.example.app.mappers.EmployeeMapper.mapToEmployeeDto;
import static com.example.app.mappers.EmployeeMapper.updateEmployee;

@Service
public class EmployeeManager implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public DataResult<PageResult<EmployeeDto>> getAllEmployees(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> page = employeeDao.findAll(pageable);
		PageResult<EmployeeDto> result = new PageResult<>();
		result.setPageNo(page.getNumber());
		result.setPageSize(page.getSize());
		result.setTotalPages(page.getTotalPages());
		result.setRows(page.getContent().stream().map(employee -> mapToEmployeeDto(employee)).collect(Collectors.toList()));
		return new SuccessDataResult<PageResult<EmployeeDto>>(result);
	}

	@Override
	public DataResult<EmployeeDto> getEmployeeById(int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();
		return new SuccessDataResult<EmployeeDto>(mapToEmployeeDto(employee));
	}

	@Override
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = mapToEmployee(employeeDto);
		Employee createdEmployee = employeeDao.save(employee);
		return new SuccessDataResult<EmployeeDto>(mapToEmployeeDto(createdEmployee));
	}

	@Override
	public DataResult<EmployeeDto> updateEmployeeById(EmployeeDto employeeDto, int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();	
		Employee updatedEmployee = employeeDao.save(updateEmployee(employeeDto, employee));
		return new SuccessDataResult<EmployeeDto>(mapToEmployeeDto(updatedEmployee));
	}

	@Override
	public Result deleteEmployee(int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();
		employeeDao.delete(employee);
		return new SuccessResult("Employee was removed successfully");
	}

}
