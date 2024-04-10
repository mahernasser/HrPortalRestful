package com.llun.service;


import com.llun.dto.DepartmentDto;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.DepartmentMapper;
import com.llun.persistence.entity.Department;
import com.llun.persistence.repository.DepartmentRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DepartmentService {

    @Inject
    private DepartmentRepo departmentRepository;


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
        DepartmentDto departmentDto = getDepartmentById(id);
        if (departmentDto.id() == null) {
            throw new ResourceNotFoundException("Department not found with id: " + id, "/departments/" + id);
        }
        departmentRepository.deleteDepartment(id);
    }

    public DepartmentDto getDepartmentById(Integer id) {
        Optional<Department> department = departmentRepository.getDepartmentById(id);
        return department.map(DepartmentMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id, "/departments/" + id));
    }

    public DepartmentDto updateDepartment(Integer id, DepartmentDto departmentDto) {
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentDto);
        department.setId(id);
        Department updatedDepartment = departmentRepository.updateDepartment(department);
        return DepartmentMapper.INSTANCE.toDto(updatedDepartment);
    }
}