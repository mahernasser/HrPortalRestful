package com.llun.service;

import com.llun.dto.LocationDto;
import com.llun.error.ResourceNotFoundException;
import com.llun.mapper.LocationMapper;
import com.llun.persistence.entity.Location;
import com.llun.persistence.repository.LocationRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocationService {

    private LocationRepo locationRepository = new LocationRepo();

    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.getAllLocations();
        return locations.stream()
                .map(LocationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public LocationDto getLocationById(Integer id) {
        Optional<Location> location = locationRepository.getLocationById(id);
        return location.map(LocationMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id, "/locations/" + id));
    }

    public LocationDto createLocation(LocationDto locationDto) {
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        Location savedLocation = locationRepository.createLocation(location);
        return LocationMapper.INSTANCE.toDto(savedLocation);
    }

    public LocationDto updateLocation(Integer id, LocationDto locationDto) {
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        location.setId(id);
        Location updatedLocation = locationRepository.updateLocation(location);
        return LocationMapper.INSTANCE.toDto(updatedLocation);
    }

    public void deleteLocation(Integer id) {
        locationRepository.deleteLocation(id);
    }
}