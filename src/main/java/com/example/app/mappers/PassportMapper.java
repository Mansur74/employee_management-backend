package com.example.app.mappers;

import java.util.stream.Collectors;

import com.example.app.dtos.CountryDto;
import com.example.app.dtos.PassportDto;
import com.example.app.entities.Passport;

public class PassportMapper {
	
	public static Passport mapToPassport(PassportDto passportDto)
	{
		return Passport.builder()
				.id(passportDto.getId())
				.passportNumber(passportDto.getPassportNumber())
				.validDateTime(passportDto.getValidDateTime())
				.build();
	}
	
	public static PassportDto mapToPassportDto(Passport passport)
	{
		return PassportDto.builder()
				.id(passport.getId())
				.passportNumber(passport.getPassportNumber())
				.validDateTime(passport.getValidDateTime())
				.countries(passport.getCountries() == null ? null : passport.getCountries().stream().map(country -> CountryDto.builder()
						.id(country.getId())
						.countryName(country.getCountryName())
						.capitalCity(country.getCapitalCity())
						.population(country.getPopulation())
						.imgURL(country.getImgURL())
						.build()).collect(Collectors.toList()))
				.build();
	}

}
