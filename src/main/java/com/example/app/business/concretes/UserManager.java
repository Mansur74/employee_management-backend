package com.example.app.business.concretes;

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

import com.example.app.business.abstracts.UserService;
import com.example.app.core.dataAccess.abstracts.UserDao;
import com.example.app.core.dtos.UserDto;
import com.example.app.core.entities.UserEntity;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.dataAccess.abstracts.RoleDao;
import com.example.app.entities.Role;


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
		UserEntity user = mapToUser(userDto);
		Role role = roleDao.findByName("ADMIN");
		user.setRoles(Arrays.asList(role));
		UserEntity createdUser = userDao.save(user);
		return new SuccessDataResult<UserDto>(mapToUserDto(createdUser));
	}
	
	public DataResult<UserDto> getUserById(int id)
	{
		UserEntity user = userDao.findById(id).orElseThrow();
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}
	
	public DataResult<UserDto> getUserByEmail(String email)
	{
		UserEntity user = userDao.findByEmail(email);
		return new SuccessDataResult<UserDto>(mapToUserDto(user));
	}

	@Override
	public DataResult<List<UserDto>> getAllUsers() {
		List<UserEntity> users = this.userDao.findAll();
		return new SuccessDataResult<List<UserDto>>(users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList()));
	}
	
	
	
}
