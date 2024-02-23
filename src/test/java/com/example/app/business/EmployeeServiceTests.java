package com.example.app.business;

import com.example.app.business.concretes.EmployeeManager;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dataAccess.abstracts.EmployeeDao;
import com.example.app.dtos.EmployeeDto;
import com.example.app.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeManager employeeManager;

    @Test
    public void EmployeeService_createEmployee()
    {
        Employee employee = Mockito.mock(Employee.class);
        EmployeeDto employeeDto = EmployeeDto
                .builder()
                .firstName("test")
                .build();

        when(employeeDao.save(Mockito.any(Employee.class))).thenReturn(employee);
        DataResult<EmployeeDto> result = employeeManager.createEmployee(employeeDto);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void EmployeeService_getEmployeeById()
    {
        Employee employee = Mockito.mock(Employee.class);

        when(employeeDao.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(employee));
        DataResult<EmployeeDto> result = employeeManager.getEmployeeById(Mockito.anyInt());
        Assertions.assertThat(result).isNotNull();
    }
}
