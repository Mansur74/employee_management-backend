package com.example.app.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
