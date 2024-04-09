package com.llun.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Size(max = 20)
    @Column(name = "job_id", nullable = false, length = 20)
    private String jobId;

    @Size(max = 50)
    @Column(name = "job_title", length = 50)
    private String jobTitle;

    @Column(name = "min_salary", precision = 10, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 10, scale = 2)
    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<JobHistory> jobHistories = new LinkedHashSet<>();

}