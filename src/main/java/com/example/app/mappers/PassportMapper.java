package com.example.app.mappers;

import com.example.app.dtos.PassportDto;
import com.example.app.models.Passport;

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
				.build();
	}

}
