package com.example.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.app.business.abstracts.CountryService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.CountryDto;

@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	
	@PostMapping("/country")
	public ResponseEntity<DataResult<CountryDto>> createCountry(@RequestBody CountryDto countryDto)
	{
		DataResult<CountryDto> result = countryService.createCountry(countryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/country/{countryId}")
	public ResponseEntity<DataResult<CountryDto>> updateCountry(@RequestBody CountryDto countryDto, @PathVariable int countryId)
	{
		DataResult<CountryDto> result = countryService.updateCountry(countryDto, countryId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}


	@GetMapping("/countries")
	public ResponseEntity<DataResult<List<CountryDto>>> getAllCountries()
	{
		DataResult<List<CountryDto>> result = countryService.getAllCountries();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
