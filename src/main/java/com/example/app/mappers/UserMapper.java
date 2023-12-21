package com.example.app.mappers;

import com.example.app.dtos.UserDto;
import com.example.app.models.User;

public class UserMapper {
	public static User mapToUser(UserDto userDto)
	{
		return User.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.userName(userDto.getUserName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.createdAt(userDto.getCreatedAt())
				.upDatedAt(userDto.getUpDatedAt())
				.build();
	}
	
	public static UserDto mapToUserDto(User user)
	{
		return UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.userName(user.getUserName())
				.email(user.getEmail())
				.password(user.getPassword())
				.createdAt(user.getCreatedAt())
				.upDatedAt(user.getUpDatedAt())
				.build();
	}
}
