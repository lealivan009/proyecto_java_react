package controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.MedicalService;

@Path("/specialists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialistController{

    @Inject
    private MedicalService medicalService;

    @GET
    public Response getAllSpecialists(){
        return Response.ok(medicalService.getAllSpeciality()).build();
    }
}