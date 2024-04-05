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
@Table(name = "job_titles")
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employees = new LinkedHashSet<>();

}