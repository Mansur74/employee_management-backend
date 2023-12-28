package com.example.app.services.abstracts;

import com.example.app.dtos.PassportDto;
import com.example.app.results.DataResult;

public interface PassportService {
	DataResult<PassportDto> createPassport(PassportDto passportDto, int employeeId);
	DataResult<PassportDto> getPassportById(int passportId);
}
