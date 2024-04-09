package com.llun.mapper;

import com.llun.dto.DepartmentDto;
import com.llun.persistence.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(target = "jobHistories", ignore = true)
    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);
}