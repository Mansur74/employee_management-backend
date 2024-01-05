package com.example.app.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.business.abstracts.PassportService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.core.utilities.results.SuccessResult;
import com.example.app.dataAccess.abstracts.CountryDao;
import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.dataAccess.abstracts.PassportDao;
import com.example.app.dtos.PassportDto;
import com.example.app.entities.Employee;
import com.example.app.entities.Passport;

import static com.example.app.mappers.PassportMapper.mapToPassport;
import static com.example.app.mappers.PassportMapper.mapToPassportDto;
import static com.example.app.mappers.PassportMapper.updatePassport;

import java.util.stream.Collectors;

@Service
public class PassportManager implements PassportService{
	
	@Autowired
	private PassportDao passportDao;

	@Autowired 
	private EmployeeDao employeeDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Override
	public DataResult<PassportDto> createPassport(PassportDto passportDto, int employeeId) {
		Employee employee = employeeDao.findById(employeeId).get();
		Passport passport = mapToPassport(passportDto);
		passport.setEmployee(employee);
		passport.setCountries(passportDto.getCountries().stream().map(country -> countryDao.findById(country.getId()).get()).collect(Collectors.toList()));
		Passport createdPassport = passportDao.save(passport);
		return new SuccessDataResult<PassportDto>(mapToPassportDto(createdPassport));
		
	}

	@Override
	public DataResult<PassportDto> getPassportById(int passportId) {
		Passport passport = passportDao.findById(passportId).get();
		return new SuccessDataResult<PassportDto>(mapToPassportDto(passport));
	}

	@Override
	public Result deletePassport(int passportId) {
		Passport passport = passportDao.findById(passportId).get();
		passportDao.delete(passport);
		return new SuccessResult("Passport was deleted");
	}

	@Override
	public DataResult<PassportDto> updatePassportById(PassportDto passportDto, int passportId) {
		Passport passport = passportDao.findById(passportId).get();
		Passport updatedPassport = updatePassport(passportDto, passport);
		passportDao.save(updatedPassport);
		return new SuccessDataResult<PassportDto>(mapToPassportDto(updatedPassport));
	}

}
