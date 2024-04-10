package com.llun.controller;

import com.llun.dto.EmployeeDto;
import com.llun.dto.JobHistoryDto;
import com.llun.service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@RequestScoped
@Path("/v1/employees")
public class EmployeeController {


    @Inject
    private  EmployeeService employeeService ;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }


    @GET
    @Path("/{id}/jobHistory")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobHistoryDto> getEmployeeJobHistory(@PathParam("id") Integer id) {
        return employeeService.getJobHistoryByEmployeeId(id);
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto getEmployeeById(@PathParam("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    //curl X DELETE http://localhost:8081/RestHrPortal/employees/105
    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Integer id) {
        employeeService.deleteEmployee(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto updateEmployee(@PathParam("id") Integer id, EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

}