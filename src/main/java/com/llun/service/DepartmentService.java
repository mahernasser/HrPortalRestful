package com.llun.service;


import com.llun.dto.DepartmentDto;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.DepartmentMapper;
import com.llun.persistence.entity.Department;
import com.llun.persistence.repository.DepartmentRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentService {

    private DepartmentRepo departmentRepository = new DepartmentRepo();


    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.createDepartment(department);
        return DepartmentMapper.INSTANCE.toDto(savedDepartment);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.getAllDepartments();
        return departments.stream()
                .map(DepartmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteDepartment(id);
    }

    public DepartmentDto getDepartmentById(Integer id) {
        Optional<Department> department = departmentRepository.getDepartmentById(id);
        return department.map(DepartmentMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id, "/departments/" + id));
    }
}