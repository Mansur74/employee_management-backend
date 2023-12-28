package com.example.app.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Passport;

public interface PassportDao extends JpaRepository<Passport, Integer>{

}
