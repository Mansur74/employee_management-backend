package com.example.app.dataAccess.abstracts;

import com.example.app.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {

}
