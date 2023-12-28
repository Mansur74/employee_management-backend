package com.example.app.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dtos.CountryDto;
import com.example.app.models.Country;
import com.example.app.repositories.abstracts.CountryDao;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.services.abstracts.CountryService;
import static com.example.app.mappers.CountryMapper.mapToCountry;
import static com.example.app.mappers.CountryMapper.mapToCountryDto;

@Service
public class CountryManager implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	@Override
	public DataResult<CountryDto> createCountry(CountryDto countryDto) {
		Country createdCountry = countryDao.save(mapToCountry(countryDto));
		return new SuccessDataResult<CountryDto>(mapToCountryDto(createdCountry));
	}

}
