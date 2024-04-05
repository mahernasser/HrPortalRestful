package com.llun.persistence.repository;


import com.llun.persistence.entity.Employee;
import com.llun.persistence.persistence_utils.TransactionUtil;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeRepo {


    public Employee findUserById(Integer id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            return entityManager.find(Employee.class, id);
        });
    }

    public List<Employee> findAllEmployees() {
        return TransactionUtil.doInTransaction(entityManager -> {
            List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
            employees.forEach(employee -> {
                employee.getEmployeeBenefits().size(); // Initialize the lazy-loaded collection
            });
            return employees;
        });
    }


    public Employee saveEmployee(Employee employee) {
        return TransactionUtil.doInTransaction(entityManager -> {
            if (employee.getId() == null) {
                entityManager.persist(employee);
                return employee;
            } else {
                return entityManager.merge(employee);
            }
        });
    }

    public void deleteEmployee(Employee employee) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            Employee managedEmployee = entityManager.find(Employee.class, employee.getId());
            if (managedEmployee != null) {
                entityManager.remove(managedEmployee);
            }
        });
    }



}
