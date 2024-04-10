package com.llun.controller;

import com.llun.dto.DepartmentDto;
import com.llun.service.DepartmentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RequestScoped
@Path("/v1/departments")
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DepartmentDto getDepartmentById(@PathParam("id") Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") Integer id) {
        departmentService.deleteDepartment(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DepartmentDto updateDepartment(@PathParam("id") Integer id, DepartmentDto departmentDto) {
        return departmentService.updateDepartment(id, departmentDto);
    }

}