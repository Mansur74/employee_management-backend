package com.example.app.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.app.mappers.UserMapper.mapToUser;
import static com.example.app.mappers.UserMapper.mapToUserDto;
import com.example.app.dtos.UserDto;
import com.example.app.models.User;
import com.example.app.repositories.abstracts.UserDao;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.services.abstracts.UserService;


@Service
public class UserManager implements UserService {
	public UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public DataResult<UserDto> createUser(UserDto userDto)
	{
		User createdUser = userDao.save(mapToUser(userDto));
		return new SuccessDataResult<UserDto>(mapToUserDto(createdUser));
	}
	
	public DataResult<UserDto> getUserById(int id)
	{
		User user = userDao.findById(id).orElseThrow();
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}
	
	public DataResult<UserDto> getUserByEmail(String email)
	{
		User user = userDao.findByEmail(email);
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}
	
	
	
}
