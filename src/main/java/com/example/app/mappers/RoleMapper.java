package com.example.app.mappers;

import com.example.app.dtos.RoleDto;
import com.example.app.entities.Role;

public class RoleMapper {
	public static RoleDto mapToRoleDto(Role role)
	{
		return RoleDto.builder()
				.id(role.getId())
				.name(role.getName())
				.build();
	}
}
