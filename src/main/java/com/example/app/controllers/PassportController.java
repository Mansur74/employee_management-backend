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

import com.example.app.dtos.PassportDto;
import com.example.app.results.DataResult;
import com.example.app.services.abstracts.PassportService;

@RestController
@RequestMapping("/api")
public class PassportController {
	
	@Autowired
	private PassportService passportService;
	
	@GetMapping("/passport/{passportId}")
	public ResponseEntity<DataResult<PassportDto>> getPassportById(@PathVariable int passportId)
	{
		DataResult<PassportDto> employee = passportService.getPassportById(passportId);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/employee/{employeeId}/passport")
	public ResponseEntity<DataResult<PassportDto>> createEmployee(@RequestBody PassportDto passportDto, @PathVariable int employeeId)
	{
		DataResult<PassportDto> passport = passportService.createPassport(passportDto, employeeId);
		return new ResponseEntity<DataResult<PassportDto>>(passport, HttpStatus.CREATED);
	}
	
}
