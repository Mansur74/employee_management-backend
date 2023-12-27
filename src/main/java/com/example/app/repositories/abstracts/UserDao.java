package com.example.app.repositories.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByEmail(String email);
	public UserEntity findByUsername(String userName);
}
