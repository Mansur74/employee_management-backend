package com.example.app.controllers;

import com.example.app.api.controllers.EmployeeController;
import com.example.app.business.concretes.EmployeeManager;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.EmployeeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {

    @Mock
    private EmployeeManager employeeManager;
    @InjectMocks
    private EmployeeController employeeController;
/*
    @Test
    public void EmployeeController_CreatePokemon_ReturnEmployeeDto()
    {
        EmployeeDto employeeDto = Mockito.mock(EmployeeDto.class);
        DataResult<EmployeeDto> result = Mockito.mock(DataResult.class);
        when(employeeManager.createEmployee(Mockito.any(EmployeeDto.class), Mockito.anyInt())).thenReturn(result);
        ResponseEntity<?> responseEntity = employeeController.createEmployee(employeeDto, Mockito.anyInt());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
*/

}
