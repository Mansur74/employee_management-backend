package com.example.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.business.abstracts.PassportService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.PassportDto;

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
	
	@PostMapping("/employee/{employeeId}")
	public ResponseEntity<DataResult<PassportDto>> createPassport(@RequestBody PassportDto passportDto, @PathVariable int employeeId)
	{
		DataResult<PassportDto> cretedPassport = passportService.createPassport(passportDto, employeeId);
		return ResponseEntity.status(HttpStatus.CREATED).body(cretedPassport);
	}
	
	@PutMapping("/passport/{passportId}")
	public ResponseEntity<DataResult<PassportDto>> updatePassportById(@RequestBody PassportDto passportDto, @PathVariable int passportId)
	{
		DataResult<PassportDto> updatedPassport = passportService.updatePassportById(passportDto, passportId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedPassport);
	}
	
	@DeleteMapping("/passport/{passport}")
	public ResponseEntity<Result> deletePassport(@PathVariable int passportId)
	{
		Result result = passportService.deletePassport(passportId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
}
