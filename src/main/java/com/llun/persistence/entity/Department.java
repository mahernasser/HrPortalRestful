package com.llun.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "department_name", length = 50)
    private String departmentName;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "location_id")
    private Integer locationId;

    @OneToMany(mappedBy = "department")
    private Set<JobHistory> jobHistories = new LinkedHashSet<>();

}