package controllers;

import java.util.UUID;

import dto.request.MedicalDtoRegister;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import models.Medical;
import services.MedicalService;
import jakarta.ws.rs.core.MediaType;

@Path("/medical")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class MedicalResource {
    
    @Inject
    private MedicalService medicalService;

    @POST
    @Path("/register")
    public Response registerMedicalUser(MedicalDtoRegister medicalRegister) throws Exception {
        try {
            medicalService.registerAndSave(medicalRegister);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    /* 
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) throws Exception {
        try {
            Medical medical = medicalService.getUserById(id);
            return Response.ok(medical).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getAllUsers(){
        System.out.println("medical users found successfully! ");
        return Response.ok().build();
    }

    */
}
