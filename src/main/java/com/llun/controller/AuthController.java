package com.llun.controller;

import com.llun.persistence.entity.User;
import com.llun.persistence.repository.AuthRepo;
import com.llun.security.JwtUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

@Path("/auth")
public class AuthController {

    @Inject
    private AuthRepo authRepository;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(User user) {

        Optional<User> optionalUser = authRepository.getUserByUsername(user.getUsername());
        if (optionalUser.isPresent() && isValidPassword(user.getPassword(), optionalUser.get().getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private boolean isValidPassword(String providedPassword, String storedPassword) {
        return BCrypt.checkpw(providedPassword, storedPassword);
    }
}