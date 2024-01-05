package com.example.app.business.abstracts;

import java.util.List;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.UserDto;

public interface UserService {
	public Result createUser(UserDto user);
	public DataResult<UserDto> getUserById(int id);
	public DataResult<UserDto> getUserByEmail(String email);
	public DataResult<List<UserDto>> getAllUsers();
}
