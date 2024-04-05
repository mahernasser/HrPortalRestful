package com.llun.mapper;

import com.llun.dto.EmployeeDTO;
import com.llun.persistence.entity.Employee;
import jakarta.ws.rs.core.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department.departmentName", target = "departmentName")
    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);
}