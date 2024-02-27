package com.example.app.dataAccess;

import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest()
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeDaoTests {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void EmployeeDao_Save_ReturnEmployee() {

        Employee employee = Employee.builder()
                .firstName("test")
                .lastName("test")
                .salary(25)
                .hiringDate(LocalDateTime.MAX)
                .imgURL("test")
                .age(25)
                .gender("test")
                .department("test")
                .description("test")
                .build();
        Employee savedEmployee = employeeDao.save(employee);
        Assertions.assertEquals("test", savedEmployee.getFirstName());
    }
}
