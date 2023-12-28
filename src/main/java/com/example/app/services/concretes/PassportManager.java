package com.example.app.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dtos.PassportDto;
import com.example.app.models.Employee;
import com.example.app.models.Passport;
import com.example.app.repositories.abstracts.EmployeeDao;
import com.example.app.repositories.abstracts.PassportDao;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.services.abstracts.PassportService;
import static com.example.app.mappers.PassportMapper.mapToPassport;
import static com.example.app.mappers.PassportMapper.mapToPassportDto;

@Service
public class PassportManager implements PassportService{
	
	@Autowired
	private PassportDao passportDao;

	@Autowired 
	private EmployeeDao employeeDao;
	
	@Override
	public DataResult<PassportDto> createPassport(PassportDto passportDto, int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();
		Passport passport = mapToPassport(passportDto);
		passport.setEmployee(employee);
		Passport createdPassport = passportDao.save(passport);
		return new SuccessDataResult<PassportDto>(mapToPassportDto(createdPassport));
		
	}

	@Override
	public DataResult<PassportDto> getPassportById(int passportId) {
		Passport passport = passportDao.findById(passportId).get();
		return new SuccessDataResult<PassportDto>(mapToPassportDto(passport));
	}

}
