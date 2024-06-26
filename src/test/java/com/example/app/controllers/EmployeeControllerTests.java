package com.example.app.controllers;

import com.example.app.api.controllers.EmployeeController;
import com.example.app.business.abstracts.EmployeeService;
import com.example.app.business.abstracts.UserService;
import com.example.app.business.concretes.EmployeeManager;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.PageResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.dtos.EmployeeDto;
import com.example.app.dtos.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {

    @Mock
    private UserService userService;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void EmployeeController_CreateEmployee_ReturnDataResult()
    {
        EmployeeDto employeeDto = Mockito.mock(EmployeeDto.class);
        DataResult<EmployeeDto> result = Mockito.mock(DataResult.class);
        when(employeeService.createEmployee(employeeDto)).thenReturn(result);
        ResponseEntity<?> responseEntity = employeeController.createEmployee(employeeDto);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void EmployeeController_GetAllEmployees_ReturnDataResult()
    {
        DataResult<PageResult<EmployeeDto>> result = Mockito.mock(DataResult.class);
        when(employeeService.getAllEmployees(1, 10)).thenReturn(result);
        ResponseEntity<?> responseEntity = employeeController.getAllEmployees(1, 10);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void EmployeeController_DeleteEmployee_ReturnResult()
    {
        Result result = Mockito.mock(Result.class);
        when(employeeService.deleteEmployee(5)).thenReturn(result);
        ResponseEntity<?> responseEntity = employeeController.deleteEmployee( 5);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



}
