package com.example.app.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.app.mappers.UserMapper.mapToUser;
import static com.example.app.mappers.UserMapper.mapToUserDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.dtos.UserDto;
import com.example.app.models.Role;
import com.example.app.models.User;
import com.example.app.repositories.abstracts.RoleDao;
import com.example.app.repositories.abstracts.UserDao;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.services.abstracts.UserService;


@Service
public class UserManager implements UserService {
	public UserDao userDao;
	public RoleDao roleDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	public UserManager(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}
	
	public DataResult<UserDto> createUser(UserDto userDto)
	{

		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = mapToUser(userDto);
		Role role = roleDao.findByName("ADMIN");
		user.setRoles(Arrays.asList(role));
		User createdUser = userDao.save(user);
		return new SuccessDataResult<UserDto>(mapToUserDto(createdUser));
	}
	
	public DataResult<UserDto> getUserById(int id)
	{
		User user = userDao.findById(id).orElseThrow();
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}
	
	public DataResult<UserDto> getUserByEmail(String email)
	{
		User user = userDao.findByEmail(email).get();
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}

	@Override
	public DataResult<List<UserDto>> getAllUsers() {
		List<User> users = this.userDao.findAll();
		return new SuccessDataResult<List<UserDto>>(users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList()));
	}
	
	
	
}
