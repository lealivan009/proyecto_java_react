package controllers;

import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import io.quarkus.vertx.http.runtime.attribute.ResponseHeaderAttribute;
import jakarta.annotation.security.PermitAll;
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

@Path("/medical")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class MedicalController {
    
    @Inject
    private MedicalService medicalService;

    // Método POST para registrar un nuevo usuario médico
    @POST
    @Path("/register")
    public Response registerMedicalUser(MedicalDtoRegister medicalRegister) throws Exception {
        // Llama al servicio para registrar y guardar un nuevo usuario médico
        medicalService.registerAndSave(medicalRegister);
        // Devuelve una respuesta con el estado 201 (CREATED) si la operación es exitosa
        return Response.status(Response.Status.CREATED).build();
    }
     
    // Método GET para obtener un usuario médico por su ID
    @GET
    @Path("/{id}")
    public Response getMedicalById(@PathParam("id") UUID id) throws Exception {
        // Obtiene el usuario médico por su ID utilizando el servicio
        Medical medical = medicalService.getMedicalById(id);
        // Devuelve una respuesta con el estado 200 (OK) y el objeto médico si se encuentra
        return Response.ok(medical).build();
    }

    // Método GET para obtener todos los usuarios médicos (actualmente solo imprime un mensaje)
    @GET
    public Response getAllUsers(){
        // Devuelve una respuesta con el estado 200 (OK)
        return Response.ok(medicalService.findAll()).build();
    }

    // Toma un Medical y modifica sus horarios
    @PUT
    @Path("/{id}/schedules")
    public Response updateSchedules(@PathParam("id") UUID id, SchedulesDtoUpdate scheduleDto) throws Exception{
        medicalService.modifySchedules(id, scheduleDto);
        return Response.noContent().build();
    }
}
