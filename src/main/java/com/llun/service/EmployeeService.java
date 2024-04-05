package com.llun.service;

import com.llun.dto.EmployeeDTO;
import com.llun.persistence.entity.Employee;
import com.llun.persistence.repository.EmployeeRepo;
import com.llun.mapper.EmployeeMapper;
import com.llun.error.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService() {
        this.employeeRepo = new EmployeeRepo();
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepo.findUserById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
        return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAllEmployees();
        return employees.stream()
                .map(EmployeeMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO
    saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
        employee = employeeRepo.saveEmployee(employee);
        return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepo.findUserById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepo.deleteEmployee(employee);
    }

    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO updatedEmployeeDTO) {
        Employee existingEmployee = employeeRepo.findUserById(id);
        if (existingEmployee == null) {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }

        // Create a new EmployeeDTO with the updated fields. Use the existing value if the field is null.
        EmployeeDTO newEmployeeDTO = new EmployeeDTO(
                id,
                updatedEmployeeDTO.firstName() != null ? updatedEmployeeDTO.firstName() : existingEmployee.getFirstName(),
                updatedEmployeeDTO.lastName() != null ? updatedEmployeeDTO.lastName() : existingEmployee.getLastName(),
                updatedEmployeeDTO.email() != null ? updatedEmployeeDTO.email() : existingEmployee.getEmail(),
                updatedEmployeeDTO.phoneNumber() != null ? updatedEmployeeDTO.phoneNumber() : existingEmployee.getPhoneNumber(),
                updatedEmployeeDTO.hireDate() != null ? updatedEmployeeDTO.hireDate() : existingEmployee.getHireDate(),
                updatedEmployeeDTO.jobId() != null ? updatedEmployeeDTO.jobId() : (existingEmployee.getJob() != null ? existingEmployee.getJob().getId() : null),
                updatedEmployeeDTO.salary() != null ? updatedEmployeeDTO.salary() : existingEmployee.getSalary(),
                updatedEmployeeDTO.managerId() != null ? updatedEmployeeDTO.managerId() : (existingEmployee.getManager() != null ? existingEmployee.getManager().getId() : null),
                updatedEmployeeDTO.departmentName() != null ? updatedEmployeeDTO.departmentName() : (existingEmployee.getDepartment() != null ? existingEmployee.getDepartment().getDepartmentName() : null)
        );

        // Map the newEmployeeDTO to an Employee entity and save it to the database.
        Employee updatedEmployee = EmployeeMapper.INSTANCE.toEntity(newEmployeeDTO);
        updatedEmployee = employeeRepo.saveEmployee(updatedEmployee);

        return EmployeeMapper.INSTANCE.toDTO(updatedEmployee);
    }
}