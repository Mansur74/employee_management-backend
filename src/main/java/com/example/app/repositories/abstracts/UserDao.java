package com.example.app.repositories.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.User;

public interface UserDao extends JpaRepository<User, Integer> {
	public Optional<User> findByEmail(String email);
	public User findByUsername(String userName);
}
