package com.llun.controller;

import com.llun.dto.EmployeeDto;
import com.llun.dto.JobHistoryDto;
import com.llun.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        EmployeeDto employeeDto = new EmployeeDto(
                9990,
                "Maher",
                "Nasser",
                "maher.naser.gh@gmail.com",
                "01011401989",
                LocalDate.now(),
                "job1",
                new BigDecimal("1000.00"),
                1,
                1
        );
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDto));
        List<EmployeeDto> result = employeeController.getAllEmployees();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(employeeDto);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(
                55,
                "Maher",
                "Nasser",
                "maher.naser.gh@gmail.com",
                "01011401989",
                LocalDate.now(),
                "job1",
                new BigDecimal("1000.00"),
                1,
                1
        );
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);
        EmployeeDto result = employeeController.createEmployee(employeeDto);
        assertThat(result).isEqualTo(employeeDto);
        verify(employeeService, times(1)).createEmployee(employeeDto);
    }

}