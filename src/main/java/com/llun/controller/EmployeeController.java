package com.llun.controller;

import com.llun.dto.EmployeeDTO;
import com.llun.service.EmployeeService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController() {
        this.employeeService = new EmployeeService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return Response.ok(employees).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Integer id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return Response.ok(employee).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
        return Response.ok(savedEmployee).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") Integer id, EmployeeDTO updatedEmployeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, updatedEmployeeDTO);
        return Response.ok(updatedEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Integer id) {
        employeeService.deleteEmployee(id);
        return Response.noContent().build();
    }
}