package com.example.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
