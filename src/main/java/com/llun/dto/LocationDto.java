package com.llun.dto;


public record LocationDto (
        Integer id,
        String streetAddress,
        String postalCode,
        String city,
        String stateProvince,
        String countryId
) {}