package com.llun.controller;


import com.llun.dto.JobDto;
import com.llun.service.JobService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/jobs")
public class JobController {

    private final JobService jobService = new JobService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto createJob(JobDto jobDto) {
        return jobService.createJob(jobDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto getJobById(@PathParam("id") String id) {
        return jobService.getJobById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteJob(@PathParam("id") String id) {
        jobService.deleteJob(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto updateJob(@PathParam("id") String id, JobDto jobDto) {
        return jobService.updateJob(id, jobDto);
    }
}
