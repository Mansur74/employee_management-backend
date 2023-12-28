package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.CountryDto;
import com.example.app.results.DataResult;
import com.example.app.services.abstracts.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@PostMapping("/country")
	public ResponseEntity<DataResult<CountryDto>> createCountry(@RequestBody CountryDto countryDto)
	{
		DataResult<CountryDto> createdCountryDto = countryService.createCountry(countryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCountryDto);
	}
}
