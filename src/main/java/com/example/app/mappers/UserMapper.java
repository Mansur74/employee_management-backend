package com.example.app.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.dtos.EmployeeDto;
import com.example.app.dtos.RoleDto;
import com.example.app.dtos.UserDto;
import com.example.app.entities.Role;
import com.example.app.entities.UserEntity;

import static com.example.app.mappers.RoleMapper.mapToRoleDto;

public class UserMapper {
	
	public static UserEntity mapToUser(UserDto userDto)
	{
		return UserEntity.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.userName(userDto.getUserName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.build();
	}
	
	public static UserDto mapToUserDto(UserEntity user)
	{

		return UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.userName(user.getUserName())
				.email(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRoles() == null ? null : user.getRoles().stream()
						.map(role -> mapToRoleDto(role)).collect(Collectors.toList()))
				.build();
	}
}
