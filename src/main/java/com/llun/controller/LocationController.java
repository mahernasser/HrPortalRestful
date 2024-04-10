package com.llun.controller;

import com.llun.dto.LocationDto;
import com.llun.service.LocationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/locations")
public class LocationController {

    private final LocationService locationService = new LocationService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LocationDto createLocation(LocationDto locationDto) {
        return locationService.createLocation(locationDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LocationDto getLocationById(@PathParam("id") Integer id) {
        return locationService.getLocationById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Integer id) {
        locationService.deleteLocation(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LocationDto updateLocation(@PathParam("id") Integer id, LocationDto locationDto) {
        return locationService.updateLocation(id, locationDto);
    }
}