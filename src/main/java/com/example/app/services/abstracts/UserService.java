package com.example.app.services.abstracts;

import com.example.app.dtos.UserDto;
import com.example.app.results.DataResult;

public interface UserService {
	public DataResult<UserDto> createUser(UserDto user);
	public DataResult<UserDto> getUserById(int id);
	public DataResult<UserDto> getUserByEmail(String email);
}
