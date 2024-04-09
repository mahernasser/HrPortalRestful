package com.llun.mapper;

import com.llun.dto.JobDto;
import com.llun.persistence.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(target = "jobHistories", ignore = true)
    JobDto toDto(Job job);
    Job toEntity(JobDto jobDto);
}