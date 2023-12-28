package com.example.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
	
	private int id;
	private String countryName;
	private String capitalCity;
	private Double population;
	private String imgURL;
	
}
