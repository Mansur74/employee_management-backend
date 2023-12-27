package com.example.app.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
