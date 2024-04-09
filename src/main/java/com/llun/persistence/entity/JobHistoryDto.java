package com.llun.persistence.entity;

import com.llun.dto.DepartmentDto;
import com.llun.dto.EmployeeDto;
import com.llun.dto.JobDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link JobHistory}
 */
public record JobHistoryDto(Integer id, EmployeeDto employee, LocalDate startDate, LocalDate endDate, JobDto job,
                            DepartmentDto department) implements Serializable {
}