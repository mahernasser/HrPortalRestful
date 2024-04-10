package com.llun.persistence.repository;

import static org.mockito.Mockito.*;

import com.llun.controller.EmployeeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import com.llun.dto.EmployeeDto;
import com.llun.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        EmployeeDto employeeDto = new EmployeeDto(1, "Maher", "Nasser", "maher.nasser@yahoo.com", "1234567890", LocalDate.now(), "job1", new BigDecimal("1000.00"), 1, 1);
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDto));
        List<EmployeeDto> result = employeeController.getAllEmployees();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(employeeDto);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(1, "Maher", "Nasser", "maher.nasser@yahoo.com", "1234567890", LocalDate.now(), "job1", new BigDecimal("1000.00"), 1, 1);
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);
        EmployeeDto result = employeeController.createEmployee(employeeDto);
        assertThat(result).isEqualTo(employeeDto);
        verify(employeeService, times(1)).createEmployee(employeeDto);
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeDto employeeDto = new EmployeeDto(1, "Maher", "Nasser", "maher.nasser@yahoo.com", "1234567890", LocalDate.now(), "job1", new BigDecimal("1000.00"), 1, 1);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeDto);

        EmployeeDto result = employeeController.getEmployeeById(1);

        assertThat(result).isEqualTo(employeeDto);

        verify(employeeService, times(1)).getEmployeeById(1);
    }

    @Test
    public void testDeleteEmployee() {
        employeeController.deleteEmployee(1);

        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(1, "Maher", "Nasser", "maher.nasser@yahoo.com", "1234567890", LocalDate.now(), "job1", new BigDecimal("1000.00"), 1, 1);
        when(employeeService.updateEmployee(1, employeeDto)).thenReturn(employeeDto);

        EmployeeDto result = employeeController.updateEmployee(1, employeeDto);

        assertThat(result).isEqualTo(employeeDto);

        verify(employeeService, times(1)).updateEmployee(1, employeeDto);
    }
}
