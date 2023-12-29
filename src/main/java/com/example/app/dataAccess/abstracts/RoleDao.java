package com.example.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
