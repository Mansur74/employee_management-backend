package com.example.app.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.dtos.EmployeeDto;
import com.example.app.dtos.RoleDto;
import com.example.app.dtos.UserDto;
import com.example.app.models.Role;
import com.example.app.models.UserEntity;

import static com.example.app.mappers.EmployeeMapper.mapToEmployee;
import static com.example.app.mappers.EmployeeMapper.mapToEmployeeDto;
import static com.example.app.mappers.RoleMapper.mapToRoleDto;

public class UserMapper {
	
	public static UserEntity mapToUser(UserDto userDto)
	{
		return UserEntity.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.username(userDto.getUsername())
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
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRoles() == null ? null : user.getRoles().stream()
						.map(role -> RoleDto.builder()
								.id(role.getId())
								.name(role.getName())
								.build()).collect(Collectors.toList()))
				.build();
	}
}
