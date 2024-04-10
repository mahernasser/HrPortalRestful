package com.llun.service;


import com.llun.dto.JobHistoryDto;
import com.llun.error.DuplicateIdException;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.JobHistoryMapper;
import com.llun.persistence.entity.JobHistory;
import com.llun.persistence.repository.JobHistoryRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobHistoryService {

    private final JobHistoryRepo jobHistoryRepository = new JobHistoryRepo();

    public JobHistoryDto createJobHistory(JobHistoryDto jobHistoryDto) {
        Optional<JobHistory> existingJobHistory = jobHistoryRepository.getJobHistoryById(jobHistoryDto.id());
        if (existingJobHistory.isPresent()) {
            throw new DuplicateIdException("JobHistory already exists with id: " + jobHistoryDto.id(), "/jobHistories/" + jobHistoryDto.id());
        } else {
            JobHistory jobHistory = JobHistoryMapper.INSTANCE.toEntity(jobHistoryDto);
            JobHistory savedJobHistory = jobHistoryRepository.createJobHistory(jobHistory);
            return JobHistoryMapper.INSTANCE.toDto(savedJobHistory);
        }
    }

    public List<JobHistoryDto> getAllJobHistories() {
        List<JobHistory> jobHistories = jobHistoryRepository.getAllJobHistories();
        return jobHistories.stream()
                .map(JobHistoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public JobHistoryDto getJobHistoryById(Integer id) {
        Optional<JobHistory> jobHistory = jobHistoryRepository.getJobHistoryById(id);
        return jobHistory.map(JobHistoryMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("JobHistory not found with id: " + id, "/jobHistories/" + id));
    }

    public JobHistoryDto updateJobHistory(Integer id, JobHistoryDto jobHistoryDto) {
        JobHistory jobHistory = JobHistoryMapper.INSTANCE.toEntity(jobHistoryDto);
        jobHistory.setId(id);
        JobHistory updatedJobHistory = jobHistoryRepository.updateJobHistory(jobHistory);
        return JobHistoryMapper.INSTANCE.toDto(updatedJobHistory);
    }

    public void deleteJobHistory(Integer id) {
        jobHistoryRepository.deleteJobHistory(id);
    }
}