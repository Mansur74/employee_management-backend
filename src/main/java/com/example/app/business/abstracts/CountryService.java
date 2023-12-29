package com.example.app.business.abstracts;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.CountryDto;

public interface CountryService {
	public DataResult<CountryDto> createCountry(CountryDto countryDto);
}
