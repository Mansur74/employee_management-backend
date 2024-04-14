package com.example.app.business;

import com.example.app.business.concretes.EmployeeManager;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.dataAccess.abstracts.UserDao;
import com.example.app.dtos.EmployeeDto;
import com.example.app.entities.Employee;
import com.example.app.entities.UserEntity;
import com.example.app.security.SecurityUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeDao employeeDao;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private EmployeeManager employeeManager;

    @Test
    public void EmployeeService_createEmployee()
    {
        UserEntity user = UserEntity.builder().id(1).email("test@hotmail.com").build();
        EmployeeDto employeeDto = EmployeeDto
                .builder()
                .firstName("test")
                .build();

        Employee employee = Mockito.mock(Employee.class);

        when(userDao.findByEmail(Mockito.any())).thenReturn(user);
        when(employeeDao.save(Mockito.any(Employee.class))).thenReturn(employee);
        DataResult<EmployeeDto> result = employeeManager.createEmployee(employeeDto);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void EmployeeService_updateEmployee()
    {
        UserEntity user = UserEntity.builder().id(1).email("test@hotmail.com").build();
        Employee employee = Employee.builder().user(user).id(5).build();

        EmployeeDto employeeDto = EmployeeDto
                .builder()
                .firstName("test")
                .build();
        when(userDao.findByEmail(Mockito.any())).thenReturn(user);
        when(employeeDao.findById(5)).thenReturn(Optional.of(employee));
        when(employeeDao.save(Mockito.any(Employee.class))).thenReturn(employee);

        DataResult<EmployeeDto> result = employeeManager.updateEmployee(employeeDto,5);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void EmployeeService_getEmployeeById()
    {
        UserEntity user = UserEntity.builder().id(1).email("test@hotmail.com").build();
        Employee employee = Employee.builder().user(user).id(5).build();

        when(userDao.findByEmail(Mockito.any())).thenReturn(user);
        when(employeeDao.findById(5)).thenReturn(Optional.ofNullable(employee));
        DataResult<EmployeeDto> result = employeeManager.getEmployeeById(5);
        Assertions.assertThat(result).isNotNull();
    }
}
