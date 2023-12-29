package com.example.app.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private LocalDateTime hiringDate;
	private String department;
	private int salary;
	private String description;
	private String imgURL;
	private PassportDto passport;
}
