package controllers;

import services.AppointmentService;
import services.UserService;


import java.util.UUID;

import config.annotations.UserSwaggerDocs;
import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mapper.UserMapper;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;
    @Inject
    private AppointmentService appointmentService;

    @UserSwaggerDocs.RegisterUser
    @POST
    @Path("/register")
    public Response registerUser(UserDtoRegister userRegister) throws Exception {
        userService.registerAndSave(userRegister);
        return Response.status(Response.Status.CREATED).build();
    }

    @UserSwaggerDocs.LoginUser
    @POST
    @Path("/login")
    public Response LoginUser(UserDtoLogin userLogin) throws Exception {
        return Response.ok(userService.loginUser(userLogin)).build();
    }

    @UserSwaggerDocs.UpdateUser
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") UUID id, UserDtoUpdate userUpdate) throws Exception{
        userService.updateUser(id, userUpdate);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @UserSwaggerDocs.GetUserById
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) throws Exception {
        return Response.ok(UserMapper.userToDto(userService.findUserById(id))).build();
    }

    @UserSwaggerDocs.DeleteUserById
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") UUID id) throws Exception{
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{userId}/appointments")
    public Response getApointmentsByUser(@PathParam("userId") UUID userId){
        return Response.ok(appointmentService.getAllByUser(userId)).build();
    }

    @UserSwaggerDocs.GetAllUser
    @GET
    public Response getAllUser(){
        return Response.ok(userService.findAll()).build();
    }
}
