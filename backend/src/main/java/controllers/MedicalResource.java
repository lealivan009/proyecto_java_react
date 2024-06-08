package controllers;

import java.util.UUID;

import dto.request.UserDtoRegister;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/medical")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class MedicalResource {
    
    @POST
    @Path("/register")
    public Response registerUser(UserDtoRegister userRegister) throws Exception {
        System.out.println("medical created successfully!");
        return Response.status(Response.Status.CREATED).build();
    }

     @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) throws Exception {
        System.out.println("medical user found successfully with id : "+ id);
        return Response.ok().build();
    }

    @GET
    public Response getAllUsers(){
        System.out.println("medical users found successfully! ");
        return Response.ok().build();
    }
}
