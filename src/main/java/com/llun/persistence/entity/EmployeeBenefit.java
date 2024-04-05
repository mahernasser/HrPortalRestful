package com.llun.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_benefits")

public class EmployeeBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Size(max = 100)
    @Column(name = "benefit_type", length = 100)
    private String benefitType;

    @Lob
    @Column(name = "benefit_description")
    private String benefitDescription;

    @Override
    public String toString() {
        return "EmployeeBenefit{" +
                "id=" + id +
                ", employee=" + employee +
                ", benefitType='" + benefitType + '\'' +
                ", benefitDescription='" + benefitDescription + '\'' +
                '}';
    }
}