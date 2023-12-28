package com.example.app.services.abstracts;

import com.example.app.dtos.CountryDto;
import com.example.app.results.DataResult;

public interface CountryService {
	public DataResult<CountryDto> createCountry(CountryDto countryDto);
}
