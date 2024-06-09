package controllers;

import services.impl.AppointmentServiceImp;

import java.util.UUID;

import dto.request.AppointmentDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentController {

    @Inject
    private AppointmentServiceImp appointmentService;

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
    public Response createAppointment(AppointmentDto appointmentDto) throws Exception{
        appointmentService.createAppointment(appointmentDto);
        return Response.status(Response.Status.CREATED).build();
        //lanzar excepcion si medico/usuario/horario no disponible/encontrado
    }
}
