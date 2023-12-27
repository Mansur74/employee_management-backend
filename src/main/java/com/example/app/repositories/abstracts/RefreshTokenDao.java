package com.example.app.repositories.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.RefreshToken;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);
}
