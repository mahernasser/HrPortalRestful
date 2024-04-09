package com.llun.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "street_address", length = 100)
    private String streetAddress;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "state_province", length = 50)
    private String stateProvince;

    @Size(max = 2)
    @Column(name = "country_id", length = 2)
    private String countryId;

}