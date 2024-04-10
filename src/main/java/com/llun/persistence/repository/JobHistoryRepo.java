package com.llun.persistence.repository;

import com.llun.persistence.entity.JobHistory;
import com.llun.persistence.persistence_utils.TransactionUtil;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JobHistoryRepo {

    public List<JobHistory> getAllJobHistories() {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.createQuery("SELECT j FROM JobHistory j", JobHistory.class).getResultList());
    }

    public Optional<JobHistory> getJobHistoryById(int id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            List<JobHistory> jobHistories = entityManager.createQuery("SELECT j FROM JobHistory j where j.id = :id", JobHistory.class).setParameter("id", id).getResultList();
            if (!jobHistories.isEmpty()) {
                return Optional.of(jobHistories.get(0));
            }
            return Optional.empty();
        });
    }

    public JobHistory createJobHistory(JobHistory jobHistory) {
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(jobHistory);
            return jobHistory;
        });
    }

    public JobHistory updateJobHistory(JobHistory jobHistory) {
        return TransactionUtil.doInTransaction(entityManager -> {
            JobHistory existingJobHistory = entityManager.find(JobHistory.class, jobHistory.getId());
            if (existingJobHistory != null) {
                if (jobHistory.getStartDate() != null) {
                    existingJobHistory.setStartDate(jobHistory.getStartDate());
                }
                if (jobHistory.getEndDate() != null) {
                    existingJobHistory.setEndDate(jobHistory.getEndDate());
                }
                if (jobHistory.getJob() != null) {
                    existingJobHistory.setJob(jobHistory.getJob());
                }
                if (jobHistory.getDepartment() != null) {
                    existingJobHistory.setDepartment(jobHistory.getDepartment());
                }
                return entityManager.merge(existingJobHistory);
            }
            return null;
        });
    }

    public void deleteJobHistory(Integer id) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            JobHistory jobHistory = entityManager.find(JobHistory.class, id);
            if (jobHistory != null) {
                entityManager.remove(jobHistory);
            }
        });
    }
}