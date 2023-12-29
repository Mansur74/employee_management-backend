package com.example.app.mappers;

import com.example.app.dtos.CountryDto;
import com.example.app.entities.Country;

public class CountryMapper {
	public static Country mapToCountry(CountryDto countryDto)
	{
		return Country.builder()
				.id(countryDto.getId())
				.countryName(countryDto.getCountryName())
				.capitalCity(countryDto.getCapitalCity())
				.population(countryDto.getPopulation())
				.imgURL(countryDto.getImgURL())
				.build();
	}
	
	public static CountryDto mapToCountryDto(Country country)
	{
		return CountryDto.builder()
				.id(country.getId())
				.countryName(country.getCountryName())
				.capitalCity(country.getCapitalCity())
				.population(country.getPopulation())
				.imgURL(country.getImgURL())
				.build();
	}
}
