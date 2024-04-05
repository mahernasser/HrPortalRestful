package com.llun.error;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class ResourceNotFoundException extends WebApplicationException {
    public ResourceNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorDetails(message))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public static class ErrorDetails {
        private String message;

        public ErrorDetails(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}