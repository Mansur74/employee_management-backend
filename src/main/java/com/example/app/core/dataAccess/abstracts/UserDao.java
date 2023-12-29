package com.example.app.core.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.core.entities.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);
	public UserEntity findByUsername(String userName);
}
