package com.example.app.api.controllers;

import com.example.app.business.abstracts.UserService;
import com.example.app.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.example.app.business.abstracts.EmployeeService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.PageResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.EmployeeDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/employees")
	public ResponseEntity<DataResult<PageResult<EmployeeDto>>> getAllEmployees(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = "10", required = false) int pageSize)
	{
		DataResult<PageResult<EmployeeDto>> result = employeeService.getAllEmployees(pageNo, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PostMapping("/my/employee")
	public ResponseEntity<DataResult<EmployeeDto>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto)
	{
		DataResult<EmployeeDto> result = employeeService.createEmployee(employeeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping("/my/employees")
	public ResponseEntity<DataResult<PageResult<EmployeeDto>>> getUserEmployees(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = "10", required = false) int pageSize)
	{
		DataResult<PageResult<EmployeeDto>> result = employeeService.getEmployees(pageNo, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/my/employee/{employeeId}")
	public ResponseEntity<DataResult<EmployeeDto>> getUserEmployeeById(@PathVariable("employeeId") int employeeId)
	{
		DataResult<EmployeeDto> result = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PutMapping("/my/employee/{employeeId}")
	public ResponseEntity<DataResult<EmployeeDto>> updateUserEmployee( @Valid @RequestBody EmployeeDto employeeDto, @PathVariable("employeeId") int employeeId)
	{
		DataResult<EmployeeDto> result = employeeService.updateEmployee(employeeDto, employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@DeleteMapping("/my/employee/{employeeId}")
	public ResponseEntity<Result> deleteEmployee(@PathVariable("employeeId") int employeeId)
	{
		Result result = employeeService.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
}
