package com.example.app.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportDto {
	private int id;
	private Long passportNumber;
	private LocalDateTime validDate;
	private List<CountryDto> countries;
}
