package com.example.app.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Country;

public interface CountryDao extends JpaRepository<Country, Integer> {

}
