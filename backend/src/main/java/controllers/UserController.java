package controllers;

import services.UserService;


import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/register")
    public Response registerUser(UserDtoRegister userRegister) throws Exception {
        userService.registerAndSave(userRegister);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/login")
    public Response LoginUser(UserDtoLogin userLogin) throws Exception {
        return Response.ok(userService.loginUser(userLogin)).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) throws Exception {
        return Response.ok(userService.findUserById(id)).build();
    }

    @GET
    public Response getAllUsers(){
        return Response.ok(userService.findAll()).build();
    }
}
