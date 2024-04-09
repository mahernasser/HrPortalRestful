package com.llun.mapper;


import com.llun.dto.DepartmentDto;
import com.llun.dto.EmployeeDto;
import com.llun.dto.JobDto;
import com.llun.persistence.entity.Department;
import com.llun.persistence.entity.Employee;
import com.llun.persistence.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

//    @Mapping(target = "jobHistories", ignore = true)
//    JobDto jobToJobDto(Job job);

//    @Mapping(target = "jobHistories", ignore = true)
//    DepartmentDto departmentToDepartmentDto(Department department);

    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto employeeDto);
}