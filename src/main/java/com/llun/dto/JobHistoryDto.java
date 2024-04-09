package com.llun.dto;


import java.io.Serializable;
import java.time.LocalDate;


public record JobHistoryDto(Integer id, LocalDate startDate, LocalDate endDate, JobDto job,
                            DepartmentDto department) implements Serializable {
}