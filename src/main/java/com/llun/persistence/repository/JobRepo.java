package com.llun.persistence.repository;

import com.llun.persistence.entity.Job;
import com.llun.persistence.persistence_utils.TransactionUtil;

import java.util.List;
import java.util.Optional;

public class JobRepo {

    public List<Job> getAllJobs() {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT j FROM Job j", Job.class).getResultList());
    }

    public Optional<Job> getJobById(String id) {
        return TransactionUtil.doInTransaction(entityManager ->
                Optional.ofNullable(entityManager.find(Job.class, id)));
    }

    public Job createJob(Job job) {
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(job);
            return job;
        });
    }

    public Job updateJob(Job job) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Job existingJob = entityManager.find(Job.class, job.getJobId());
            if (existingJob != null) {
                if (job.getJobTitle() != null) {
                    existingJob.setJobTitle(job.getJobTitle());
                }
                if (job.getMinSalary() != null) {
                    existingJob.setMinSalary(job.getMinSalary());
                }
                if (job.getMaxSalary() != null) {
                    existingJob.setMaxSalary(job.getMaxSalary());
                }
                return entityManager.merge(existingJob);
            }
            return null;
        });
    }

    public void deleteJob(String id) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            Job job = entityManager.find(Job.class, id);
            if (job != null) {
                entityManager.remove(job);
            }
        });
    }
}