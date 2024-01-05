package com.example.app.mappers;

import java.util.stream.Collectors;

import com.example.app.dtos.CountryDto;
import com.example.app.dtos.PassportDto;
import com.example.app.entities.Passport;

import static com.example.app.mappers.CountryMapper.mapToCountryDto;

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
				.countries(passport.getCountries().stream().map(country -> mapToCountryDto(country)).collect(Collectors.toList()))
				.build();
	}
	
	public static Passport updatePassport(PassportDto passportDto, Passport passport)
	{
		passport.setPassportNumber(passportDto.getPassportNumber());
		passport.setValidDateTime(passportDto.getValidDateTime());
		return passport;
	}
	

}
