package com.example.app.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.User;

public interface UserDao extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
}
