package controllers;

import services.AppointmentService;
import java.util.UUID;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Appointment;
import java.net.URI;

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentController {

    @Inject
    private AppointmentService appointmentService;

    @GET
    @Path("/{id}")
    //UUID como tipo de dato para id unicos en entidades JPA
    public Response getAppointmentById(@PathParam("id") UUID id) throws Exception {
        return Response.ok(appointmentService.getAppointmentById(id)).build();
    }

    @GET
    public Response getAllAppointments(){
        return Response.ok(appointmentService.getAllAppointments()).build();
    }

    @POST
    public Response createAppointment(Appointment appointment){
        return Response.ok(appointmentService.createAppointment(appointment)).build();
        //lanzar excepcion si medico/usuario/horario no disponible/encontrado
    }
}
