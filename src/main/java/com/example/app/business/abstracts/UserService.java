package com.example.app.business.abstracts;

import java.util.List;

import com.example.app.core.dtos.UserDto;
import com.example.app.core.utilities.results.DataResult;

public interface UserService {
	public DataResult<UserDto> createUser(UserDto user);
	public DataResult<UserDto> getUserById(int id);
	public DataResult<UserDto> getUserByEmail(String email);
	public DataResult<List<UserDto>> getAllUsers();
}
