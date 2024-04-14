package com.example.app.dataAccess;

import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.dataAccess.abstracts.UserDao;
import com.example.app.entities.Employee;
import com.example.app.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@DataJpaTest()
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeDaoTests {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void EmployeeDao_Save_ReturnEmployee() {
        UserEntity user = UserEntity.builder().firstName("test").build();
        userDao.save(user);

        Employee employee = Employee.builder()
                .firstName("test")
                .build();
        employee.setUser(user);
        Employee savedEmployee = employeeDao.save(employee);
        Assertions.assertEquals("test", savedEmployee.getFirstName());
    }
}
