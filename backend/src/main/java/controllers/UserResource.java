package controllers;

import services.UserService;


import java.util.UUID;

import dto.request.UserDtoRegister;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    public Response registerUser(UserDtoRegister userRegister) throws Exception {
        userService.registerAndSave(userRegister);
        return Response.status(Response.Status.CREATED).build();
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
