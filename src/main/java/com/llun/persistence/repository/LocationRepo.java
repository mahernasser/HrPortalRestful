package com.llun.persistence.repository;

import com.llun.persistence.entity.Location;
import com.llun.persistence.persistence_utils.TransactionUtil;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LocationRepo {

    public List<Location> getAllLocations() {
        return TransactionUtil.doInTransaction(entityManager ->
                entityManager.createQuery("SELECT l FROM Location l", Location.class).getResultList());
    }

    public Optional<Location> getLocationById(Integer id) {
        return TransactionUtil.doInTransaction(entityManager ->
                Optional.ofNullable(entityManager.find(Location.class, id)));
    }

    public Location createLocation(Location location) {
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(location);
            return location;
        });
    }

    public Location updateLocation(Location location) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Location existingLocation = entityManager.find(Location.class, location.getId());
            if (existingLocation != null) {
                if (location.getStreetAddress() != null) {
                    existingLocation.setStreetAddress(location.getStreetAddress());
                }
                if (location.getPostalCode() != null) {
                    existingLocation.setPostalCode(location.getPostalCode());
                }
                if (location.getCity() != null) {
                    existingLocation.setCity(location.getCity());
                }
                if (location.getStateProvince() != null) {
                    existingLocation.setStateProvince(location.getStateProvince());
                }
                if (location.getCountryId() != null) {
                    existingLocation.setCountryId(location.getCountryId());
                }
                return entityManager.merge(existingLocation);
            }
            return null;
        });
    }

    public void deleteLocation(Integer id) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> {
            Location location = entityManager.find(Location.class, id);
            if (location != null) {
                entityManager.remove(location);
            }
        });
    }
}