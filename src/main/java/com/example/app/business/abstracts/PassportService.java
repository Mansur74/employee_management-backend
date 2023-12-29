package com.example.app.business.abstracts;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.PassportDto;

public interface PassportService {
	DataResult<PassportDto> createPassport(PassportDto passportDto, int employeeId);
	DataResult<PassportDto> getPassportById(int passportId);
}
