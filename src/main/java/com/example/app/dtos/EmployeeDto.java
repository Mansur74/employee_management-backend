package com.example.app.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotEmpty(message = "firstName can not be null!")
	private String firstName;
	@NotEmpty(message = "lastName can not be null!")
	private String lastName;
	@NotEmpty(message = "gender can not be null!")
	private String gender;
	@Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 30, message = "Age should not be greater than 30")
	private int age;
	private LocalDateTime hiringDate;
	@NotEmpty(message = "department can not be null!")
	private String department;
	@NotNull
	private int salary;
	@NotEmpty(message = "description can not be null!")
	private String description;
	@NotEmpty(message = "imageURL can not be null!")
	private String imgURL;
	private PassportDto passport;
}
