package com.example.app.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dtos.EmployeeDto;
import com.example.app.models.Employee;
import com.example.app.models.User;
import com.example.app.repositories.abstracts.EmployeeDao;
import com.example.app.repositories.abstracts.UserDao;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.services.abstracts.EmployeeService;

import static com.example.app.mappers.EmployeeMapper.mapToEmployee;
import static com.example.app.mappers.EmployeeMapper.mapToEmployeeDto;

@Service
public class EmployeeManager implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public DataResult<List<EmployeeDto>> getAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		return new SuccessDataResult<List<EmployeeDto>>(employees.stream().map(employee -> mapToEmployeeDto(employee)).collect(Collectors.toList()));
	}

	@Override
	public DataResult<EmployeeDto> getEmployeeById(int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();
		return new SuccessDataResult<EmployeeDto>(mapToEmployeeDto(employee));
	}

	@Override
	public DataResult<EmployeeDto> createEmployee(EmployeeDto employeeDto, int userId) {
		
		User user = userDao.findById(userId).get();
		Employee employee = mapToEmployee(employeeDto);
		employee.setUser(user);
		Employee createdEmployee = employeeDao.save(employee);
		return new SuccessDataResult<EmployeeDto>(mapToEmployeeDto(createdEmployee));
	}

}
