package com.llun.dto;


import java.math.BigDecimal;
import java.util.Set;

public record JobDto (
        String jobId,
        String jobTitle,
        BigDecimal minSalary,
        BigDecimal maxSalary,
        Set<JobHistoryDto> jobHistories
) {}
