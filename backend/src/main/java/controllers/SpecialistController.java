package controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.impl.SpecialistServiceImp;

@Path("/specialists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SpecialistController{

    @Inject
    private SpecialistServiceImp specialistServiceImp;

    @GET
    public Response getAllSpecialists(){
        return Response.ok(specialistServiceImp.getAllSpecialists()).build();
    }
}