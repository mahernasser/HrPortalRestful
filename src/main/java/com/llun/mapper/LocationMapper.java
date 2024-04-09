package com.llun.mapper;


import com.llun.dto.LocationDto;
import com.llun.persistence.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDto toDto(Location location);

    Location toEntity(LocationDto locationDto);
}