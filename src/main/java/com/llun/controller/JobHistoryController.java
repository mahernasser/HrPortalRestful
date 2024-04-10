package com.llun.controller;


import com.llun.dto.JobHistoryDto;
import com.llun.service.JobHistoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/jobHistories")
public class JobHistoryController {

    private final JobHistoryService jobHistoryService = new JobHistoryService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobHistoryDto> getAllJobHistories() {
        return jobHistoryService.getAllJobHistories();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JobHistoryDto createJobHistory(JobHistoryDto jobHistoryDto) {
        return jobHistoryService.createJobHistory(jobHistoryDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JobHistoryDto getJobHistoryById(@PathParam("id") Integer id) {
        return jobHistoryService.getJobHistoryById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteJobHistory(@PathParam("id") Integer id) {
        jobHistoryService.deleteJobHistory(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JobHistoryDto updateJobHistory(@PathParam("id") Integer id, JobHistoryDto jobHistoryDto) {
        return jobHistoryService.updateJobHistory(id, jobHistoryDto);
    }
}