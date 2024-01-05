package com.example.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);
	public UserEntity findByUserName(String userName);
}
