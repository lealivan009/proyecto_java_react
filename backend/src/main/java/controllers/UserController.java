package controllers;

import models.User;
import services.UserService;


import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    // @Inject
    // UserService userService;

    // @GET
    // public List<User> getAllUsers() {
    // 	System.out.println("All users was showed");
    //     return userService.listAllUsers();
    // }

    // @POST
    // public Response createUser(User user) {
    //     userService.addUser(user);
    //     System.out.println("A user was created");
    //     return Response.status(Response.Status.CREATED).build();
    // }

    // @GET
    // @Path("/{id}")
    // public User getUserById(@PathParam("id") Long id) {
    // 	System.out.println("One user was showed whit id: "+ id);
    //     return userService.findUserById(id);
    // }

    // @PUT
    // @Path("/{id}")
    // public Response updateUser(@PathParam("id") Long id, User user) {
    //     // user.setId(id);
    //     userService.updateUser(user);
    //     System.out.println("One user was updated whit id: "+ id);
    //     return Response.ok().build();
    // }

    // @DELETE
    // @Path("/{id}")
    // public Response deleteUser(@PathParam("id") Long id) {
    //     userService.deleteUser(id);
    //     System.out.println("One user was deleted whit id: "+ id);
    //     return Response.noContent().build();
    // }
}
