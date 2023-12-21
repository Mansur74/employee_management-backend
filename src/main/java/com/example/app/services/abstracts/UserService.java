package com.example.app.services.abstracts;

import com.example.app.dtos.UserDto;

public interface UserService {
	public UserDto createUser(UserDto user);
	public UserDto getUserById(int id);
	public UserDto getUserByEmail(String email);
}
