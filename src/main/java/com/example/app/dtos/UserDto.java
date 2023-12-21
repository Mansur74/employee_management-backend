package com.example.app.dtos;

import java.time.LocalDateTime;

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
	String userName;
	@NotEmpty(message = "email can not be empty")
	String email;
	@Size(min = 6, max = 10, message = "passport should be at least 6, max 10 chars")
	String password;
	LocalDateTime createdAt;
	LocalDateTime upDatedAt;
}
