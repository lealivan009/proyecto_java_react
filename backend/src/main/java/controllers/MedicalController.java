package controllers;

import java.util.UUID;

import config.annotations.MedicalSwaggerDocs;
import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import models.Medical;
import services.MedicalService;
import jakarta.ws.rs.core.MediaType;

@Path("/medicals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalController {
    
    @Inject
    private MedicalService medicalService;

    @MedicalSwaggerDocs.RegisterMedical
    @POST
    @Path("/register")
    public Response registerMedical(MedicalDtoRegister medicalRegister) throws Exception {
        // Llama al servicio para registrar y guardar un nuevo usuario médico
        medicalService.registerAndSave(medicalRegister);
        // Devuelve una respuesta con el estado 201 (CREATED) si la operación es exitosa
        return Response.status(Response.Status.CREATED).build();
    }
     
    @MedicalSwaggerDocs.GetMedicalById
    @GET
    @Path("/{id}")
    public Response getMedicalById(@PathParam("id") UUID id) throws Exception {
        // Obtiene el usuario médico por su ID utilizando el servicio
        Medical medical = medicalService.getMedicalById(id);
        // Devuelve una respuesta con el estado 200 (OK) y el objeto médico si se encuentra
        return Response.ok(medical).build();
    }

    @MedicalSwaggerDocs.GetAllMedical
    @GET
    public Response getAllMedical(){
        // Devuelve una respuesta con el estado 200 (OK)
        return Response.ok(medicalService.findAllMedicalDto()).build();
    }

    // Toma un Medical y modifica sus horarios
    @MedicalSwaggerDocs.UpdateSchedules
    @PUT
    @Path("/{id}/schedules")
    public Response updateSchedules(@PathParam("id") UUID id, SchedulesDtoUpdate scheduleDto) throws Exception{
        medicalService.modifySchedules(id, scheduleDto);
        return Response.noContent().build();
    }
}
