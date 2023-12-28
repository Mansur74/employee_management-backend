package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.EmployeeDto;
import com.example.app.results.DataResult;
import com.example.app.results.PageResult;
import com.example.app.services.abstracts.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public ResponseEntity<DataResult<PageResult<EmployeeDto>>> getAllEmployees(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = "10", required = false) int pageSize)
	{
		DataResult<PageResult<EmployeeDto>> employees = employeeService.getAllEmployees(pageNo, pageSize);
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<DataResult<EmployeeDto>> getEmployeeById(int employeeId)
	{
		DataResult<EmployeeDto> employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<DataResult<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		DataResult<EmployeeDto> employee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<DataResult<EmployeeDto>>(employee, HttpStatus.CREATED);
	}
	
	@PatchMapping("/employee/{employeeId}")
	public ResponseEntity<DataResult<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("employeeId") int employeeId)
	{
		DataResult<EmployeeDto> employee = employeeService.updateEmployeeById(employeeDto, employeeId);
		return new ResponseEntity<DataResult<EmployeeDto>>(employee, HttpStatus.CREATED);
	}
	
	/*
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("user/{userId}/employee")
	public ResponseEntity<DataResult<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int userId)
	{
		DataResult<EmployeeDto> employee = employeeService.createEmployee(employeeDto, userId);
		return new ResponseEntity<DataResult<EmployeeDto>>(employee, HttpStatus.CREATED);
	}*/
	
}
