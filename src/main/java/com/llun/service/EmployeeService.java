package com.llun.service;

import com.llun.dto.EmployeeDto;
import com.llun.dto.JobHistoryDto;
import com.llun.error.DuplicateIdException;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.EmployeeMapper;
import com.llun.mapper.JobHistoryMapper;
import com.llun.persistence.entity.Employee;
import com.llun.persistence.entity.JobHistory;
import com.llun.persistence.repository.EmployeeRepo;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class EmployeeService {

    //    @Inject
    private EmployeeRepo employeeRepository = new EmployeeRepo();



    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> existingEmployee = employeeRepository.getEmployeeById(employeeDto.id());
        if (existingEmployee.isPresent()) {
            throw new DuplicateIdException("Employee already exists with id: " + employeeDto.id(), "/employees/" + employeeDto.id());
        } else {

            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDto);
            Employee savedEmployee = employeeRepository.createEmployee(employee);
            return EmployeeMapper.INSTANCE.toDto(savedEmployee);
        }
    }

    public List<JobHistoryDto> getEmployeeJobHistory(Integer id) {
        List<JobHistory> jobHistories = employeeRepository.getEmployeeJobHistory(id);
        return jobHistories.stream()
                .map(JobHistoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        return employees.stream()
                .map(EmployeeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
    /*
  List<JobHistory> getJobHistoryByEmployeeId(int id) {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT j FROM JobHistory j WHERE j.employee.id = :id", JobHistory.class)
                        .setParameter("id", id)
                        .getResultList());
    }
    * */

//    public List<JobHistoryDto> getJobHistoryByEmployeeId(Integer id) {
//        List<JobHistory> jobHistories = employeeRepository.getJobHistoryByEmployeeId(id);
//        return jobHistories.stream()
//                .map(JobHistoryMapper.INSTANCE::toDto)
//                .collect(Collectors.toList());
//    }


    public List<JobHistoryDto> getJobHistoryByEmployeeId(Integer id) {
        List<JobHistory> jobHistories = employeeRepository.getEmployeeJobHistory(id);
        return jobHistories.stream()
                .map(JobHistoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteEmployee(id);
    }

    public EmployeeDto getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.getEmployeeById(id);
        return employee.map(EmployeeMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id, "/employees/" + id));
    }






}