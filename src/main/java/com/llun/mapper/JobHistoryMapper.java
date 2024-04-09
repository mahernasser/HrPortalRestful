package com.llun.mapper;

import com.llun.dto.JobHistoryDto;
import com.llun.persistence.entity.JobHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {JobMapper.class, DepartmentMapper.class})
public interface JobHistoryMapper {
    JobHistoryMapper INSTANCE = Mappers.getMapper(JobHistoryMapper.class);

//    @Mapping(target = "employee", ignore = true)
    JobHistoryDto toDto(JobHistory jobHistory);
    JobHistory toEntity(JobHistoryDto jobHistoryDto);
}