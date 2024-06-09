package controllers;

import services.UserService;


import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
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

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") UUID id, UserDtoUpdate userUpdate) throws Exception{
        userService.updateUser(id, userUpdate);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) throws Exception {
        return Response.ok(userService.findUserById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") UUID id) throws Exception{
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @GET
    public Response getAllUsers(){
        return Response.ok(userService.findAll()).build();
    }
}
