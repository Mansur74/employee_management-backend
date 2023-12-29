package com.example.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Country;

public interface CountryDao extends JpaRepository<Country, Integer> {

}
