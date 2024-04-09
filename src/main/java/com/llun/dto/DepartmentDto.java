package com.llun.dto;


import java.util.Set;

public record DepartmentDto (
        Integer id,
        String departmentName,
        Integer managerId,
        Integer locationId,
        Set<JobHistoryDto> jobHistories
) {}