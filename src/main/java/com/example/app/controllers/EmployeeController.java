package com.example.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.EmployeeDto;
import com.example.app.models.Employee;
import com.example.app.results.DataResult;
import com.example.app.services.abstracts.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/employee")
	public ResponseEntity<DataResult<List<EmployeeDto>>> getAllEmployees()
	{
		DataResult<List<EmployeeDto>> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<DataResult<EmployeeDto>> getEmployeeById(int employeeId)
	{
		DataResult<EmployeeDto> employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/{userId}/employee")
	public ResponseEntity<DataResult<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int userId)
	{
		DataResult<EmployeeDto> employee = employeeService.createEmployee(employeeDto, userId);
		return new ResponseEntity<DataResult<EmployeeDto>>(employee, HttpStatus.CREATED);
	}
}
