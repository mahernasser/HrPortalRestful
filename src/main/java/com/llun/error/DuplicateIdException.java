package com.llun.error;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

public class DuplicateIdException extends WebApplicationException {

    public DuplicateIdException(String message, String path) {
        super(Response.status(Response.Status.CONFLICT) // 409 stats code
                .entity(new ErrorDetails(
                        LocalDateTime.now().toString(),
                        Response.Status.CONFLICT.getStatusCode(),
                        "Conflict",
                        message,
                        path))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}