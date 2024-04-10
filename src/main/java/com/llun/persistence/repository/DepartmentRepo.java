package com.llun.persistence.repository;

import com.llun.persistence.entity.Department;
import com.llun.persistence.persistence_utils.TransactionUtil;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class DepartmentRepo {

    public List<Department> getAllDepartments() {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList());
    }

    public Optional<Department> getDepartmentById(int id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            List<Department> departments = entityManager.createQuery("SELECT d FROM Department d where d.id = :id", Department.class)
                    .setParameter("id", id)
                    .getResultList();
            if (!departments.isEmpty()) {
                return Optional.of(departments.get(0));
            }
            return Optional.empty();
        });
    }

    public Department createDepartment(Department department) {
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(department);
            return department;
        });
    }

    public Department updateDepartment(Department department) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Department existingDepartment = entityManager.find(Department.class, department.getId());
            if (existingDepartment != null) {
                if (department.getDepartmentName() != null) {
                    existingDepartment.setDepartmentName(department.getDepartmentName());
                }
                if (department.getManagerId() != null) {
                    existingDepartment.setManagerId(department.getManagerId());
                }
                if (department.getLocationId() != null) {
                    existingDepartment.setLocationId(department.getLocationId());
                }
                return entityManager.merge(existingDepartment);
            }
            return null;
        });
    }

    public List<Object[]> getDepartmentsGroupedByLocation() {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT d.locationId, COUNT(d) FROM Department d GROUP BY d.locationId")
                        .getResultList());
    }


    public void deleteDepartment(Integer id) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            Department department = entityManager.find(Department.class, id);
            if (department != null) {
                entityManager.remove(department);
            }
        });
    }


}