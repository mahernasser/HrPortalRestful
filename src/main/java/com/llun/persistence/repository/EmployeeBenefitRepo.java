package com.llun.persistence.repository;


import com.llun.persistence.entity.EmployeeBenefit;
import com.llun.persistence.persistence_utils.TransactionUtil;
import jakarta.persistence.NoResultException;

import java.util.List;

public class EmployeeBenefitRepo {

    public EmployeeBenefit findBenefitById(Integer id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            return entityManager.find(EmployeeBenefit.class, id);
        });
    }

    public EmployeeBenefit findBenefitByName(String benefitName) {
        return TransactionUtil.doInTransaction(entityManager -> {
            try {
                return entityManager.createQuery("SELECT b FROM EmployeeBenefit b WHERE b.benefitType = :benefitName", EmployeeBenefit.class)
                        .setParameter("benefitName", benefitName)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    public List<EmployeeBenefit> findAllBenefits() {
        return TransactionUtil.doInTransaction(entityManager -> {
            return entityManager.createQuery("SELECT b FROM EmployeeBenefit b", EmployeeBenefit.class)
                    .getResultList();
        });
    }

    public EmployeeBenefit saveBenefit(EmployeeBenefit benefit) {
        return TransactionUtil.doInTransaction(entityManager -> {
            if (benefit.getId() == null) {
                entityManager.persist(benefit);
                return benefit;
            } else {
                return entityManager.merge(benefit);
            }
        });
    }

    public void deleteBenefit(EmployeeBenefit benefit) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            EmployeeBenefit mergedBenefit = entityManager.merge(benefit);
            entityManager.remove(mergedBenefit);
        });
    }


}