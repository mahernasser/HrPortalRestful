package com.llun.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.llun.error.ResourceNotFoundException;
import com.llun.persistence.entity.Employee;
import com.llun.persistence.entity.JobHistory;
import com.llun.persistence.persistence_utils.TransactionUtil;


public class EmployeeRepo {


    public List<Employee> getAllEmployees() {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList());
    }


    public Optional<Employee> getEmployeeById(int id) {
        return TransactionUtil.doInTransaction(entityManager ->
                Optional.ofNullable(entityManager.find(Employee.class, id)));
    }

    public List<JobHistory> getEmployeeJobHistory(int id) {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT j FROM JobHistory j WHERE j.employee.id = :id")
                        .setParameter("id", id)
                        .getResultList());
    }

    public Employee createEmployee(Employee employee) {
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(employee);
            return employee;
        });
    }


    public Employee updateEmployee(Employee employee) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee existingEmployee = entityManager.find(Employee.class, employee.getId());
            if (existingEmployee != null) {
                if (employee.getFirstName() != null) {
                    existingEmployee.setFirstName(employee.getFirstName());
                }
                if (employee.getLastName() != null) {
                    existingEmployee.setLastName(employee.getLastName());
                }
                if (employee.getEmail() != null) {
                    existingEmployee.setEmail(employee.getEmail());
                }
                if (employee.getPhoneNumber() != null) {
                    existingEmployee.setPhoneNumber(employee.getPhoneNumber());
                }
                if (employee.getHireDate() != null) {
                    existingEmployee.setHireDate(employee.getHireDate());
                }
                if (employee.getJobId() != null) {
                    existingEmployee.setJobId(employee.getJobId());
                }
                if (employee.getSalary() != null) {
                    existingEmployee.setSalary(employee.getSalary());
                }
                if (employee.getManagerId() != null) {
                    existingEmployee.setManagerId(employee.getManagerId());
                }
                if (employee.getDepartmentId() != null) {
                    existingEmployee.setDepartmentId(employee.getDepartmentId());
                }
//                if (employee.getJobHistories() != null) {
//                    existingEmployee.setJobHistories(employee.getJobHistories());
//                }
                return entityManager.merge(existingEmployee);
            }
            return null;
        });
    }



    public List<JobHistory> getJobHistoryByEmployeeId(int id) {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT j FROM JobHistory j WHERE j.employee.id = :id", JobHistory.class)
                        .setParameter("id", id)
                        .getResultList());
    }


    public void deleteEmployee(Integer id) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                entityManager.remove(employee);
            }
        });
    }

    public static void main(String[] args) {
        EmployeeRepo employeeRepo = new EmployeeRepo();
       List<JobHistory> histories = employeeRepo.getEmployeeJobHistory(105);
        System.out.println(histories.get(0).getDepartment().getDepartmentName());

    }


}