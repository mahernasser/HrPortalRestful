package com.llun.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record EmployeeDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate hireDate,
        String jobId,
        BigDecimal salary,
        Integer managerId,
        Integer departmentId
) {
}