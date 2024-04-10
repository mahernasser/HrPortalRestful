package com.llun.service;


import com.llun.dto.JobDto;
import com.llun.error.DuplicateIdException;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.JobMapper;
import com.llun.persistence.entity.Job;
import com.llun.persistence.repository.JobRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobService {

    private final JobRepo jobRepository = new JobRepo();

    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.getAllJobs();
        return jobs.stream()
                .map(JobMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public JobDto getJobById(String id) {
        Optional<Job> job = jobRepository.getJobById(id);
        return job.map(JobMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id, "/jobs/" + id));
    }

    // add the conflict exception to the createJob method
    public JobDto createJob(JobDto jobDto) {

        Job job = JobMapper.INSTANCE.toEntity(jobDto);
        Optional<Job> existingJob = jobRepository.getJobById(job.getJobId());
        if (existingJob.isPresent()) {
            throw new DuplicateIdException("Job already exists with id: " + job.getJobId(), "/jobs/" + job.getJobId());
        }
        Job savedJob = jobRepository.createJob(job);
        return JobMapper.INSTANCE.toDto(savedJob);
    }

    public JobDto updateJob(String id, JobDto jobDto) {
        Job job = JobMapper.INSTANCE.toEntity(jobDto);
        job.setJobId(id);
        Job updatedJob = jobRepository.updateJob(job);
        return JobMapper.INSTANCE.toDto(updatedJob);
    }

    public void deleteJob(String id) {
        jobRepository.deleteJob(id);
    }
}