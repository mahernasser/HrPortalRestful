package com.llun.dto;

import jakarta.ws.rs.core.Link;

import java.math.BigDecimal;
import java.time.LocalDate;
public record EmployeeDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate hireDate,
        Integer jobId,
        BigDecimal salary,
        Integer managerId,
        String departmentName
) {}