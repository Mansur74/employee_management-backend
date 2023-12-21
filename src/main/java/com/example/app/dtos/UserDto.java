package com.example.app.dtos;

import java.time.LocalDateTime;

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
	String firstName;
	String lastName;
	String userName;
	String email;
	String password;
	LocalDateTime createdAt;
	LocalDateTime upDatedAt;
}
