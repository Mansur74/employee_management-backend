package com.example.app.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
	@NotEmpty(message = "username can not be empty")
	String email;
	@NotEmpty(message = "password can not be empty")
	String password;
}
