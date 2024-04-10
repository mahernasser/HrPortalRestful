package com.llun.error;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ResourceNotFoundException extends WebApplicationException {
    public ResourceNotFoundException(String message, String path) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorDetails(
                        LocalDateTime.now(),
                        Response.Status.NOT_FOUND.getStatusCode(),
                        "Not Found",
                        message,
                        path))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}