package com.example.app.mappers;

import java.util.stream.Collectors;

import com.example.app.dtos.UserDto;
import com.example.app.entities.UserEntity;

import static com.example.app.mappers.EmployeeMapper.mapToEmployeeDto;
import static com.example.app.mappers.RoleMapper.mapToRoleDto;
import static com.example.app.mappers.UserDetailMapper.mapToUserDetailsDto;

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
				.employees(user.getEmployees() == null ? null : user.getEmployees().stream()
						.map(employee -> mapToEmployeeDto(employee)).collect(Collectors.toList()))
				.userDetail(user.getUserDetail() == null ? null : mapToUserDetailsDto(user.getUserDetail()))
				.roles(user.getRoles() == null ? null : user.getRoles().stream()
						.map(role -> mapToRoleDto(role)).collect(Collectors.toList()))
				.build();
	}
}
