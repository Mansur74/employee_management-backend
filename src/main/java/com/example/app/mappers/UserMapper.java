package com.example.app.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.dtos.UserDto;
import com.example.app.models.Role;
import com.example.app.models.User;

import static com.example.app.mappers.EmployeeMapper.mapToEmployee;
import static com.example.app.mappers.EmployeeMapper.mapToEmployeeDto;
import static com.example.app.mappers.RoleMapper.mapToRoleDto;

public class UserMapper {
	
	public static User mapToUser(UserDto userDto)
	{
		return User.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.username(userDto.getUsername())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.build();
	}
	
	public static UserDto mapToUserDto(User user)
	{

		return UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRoles() == null ? null : user.getRoles().stream().map(role -> mapToRoleDto(role)).collect(Collectors.toList()))
				.employees(user.getEmployees() == null ? null : user.getEmployees().stream().map(employee -> mapToEmployeeDto(employee)).collect(Collectors.toList()))
				.build();
	}
}
