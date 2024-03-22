package com.example.app.business.abstracts;

import java.util.List;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.CountryDto;

public interface CountryService {
	public DataResult<CountryDto> createCountry(CountryDto countryDto);
	public DataResult<CountryDto> updateCountry(CountryDto countryDto, int countryId);
	public DataResult<List<CountryDto>> getAllCountries();
}
