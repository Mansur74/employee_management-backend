package com.example.app.dtos;

import java.util.List;
import java.util.Set;

import com.example.app.models.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	int id;
	@NotEmpty(message = "firstName can not be empty")
	String firstName;
	@NotEmpty(message = "latName can not be empty")
	String lastName;
	@NotEmpty(message = "userName can not be empty")
	String username;
	@NotEmpty(message = "email can not be empty")
	String email;
	@Size(min = 6, max = 10, message = "passport should be at least 6, max 10 chars")
	String password;
	List<EmployeeDto> employees;
    List<RoleDto> roles;
}
