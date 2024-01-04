package com.example.app.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.business.abstracts.CountryService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.dataAccess.abstracts.CountryDao;
import com.example.app.dtos.CountryDto;
import com.example.app.entities.Country;

import static com.example.app.mappers.CountryMapper.mapToCountry;
import static com.example.app.mappers.CountryMapper.mapToCountryDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryManager implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	@Override
	public DataResult<CountryDto> createCountry(CountryDto countryDto) {
		Country createdCountry = countryDao.save(mapToCountry(countryDto));
		return new SuccessDataResult<CountryDto>(mapToCountryDto(createdCountry));
	}

	@Override
	public DataResult<List<CountryDto>> getAllCountries() {
		List<Country> countries = countryDao.findAll();
		List<CountryDto> countryDtos = countries.stream().map(country -> mapToCountryDto(country)).collect(Collectors.toList());
		return new SuccessDataResult<List<CountryDto>>(countryDtos);
	}

}
