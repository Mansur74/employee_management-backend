package com.example.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Passport;

public interface PassportDao extends JpaRepository<Passport, Integer>{

}
