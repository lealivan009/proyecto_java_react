package controllers;

import services.impl.AppointmentServiceImp;

import java.util.UUID;

import config.annotations.Appointments.CreateAppointment;
import config.annotations.Appointments.DeleteAppointment;
import config.annotations.Appointments.GetAllAppointment;
import config.annotations.Appointments.GetAppointmentById;
import config.annotations.Appointments.UpdateAppointment;
import dto.request.AppointmentDto;
import dto.request.NewAppointmentDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalTime;


@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentController {

    @Inject
    private AppointmentServiceImp appointmentService;

    @GET
    @Path("/{id}")
    @GetAppointmentById
    //UUID como tipo de dato para id unicos en entidades JPA
    public Response getAppointmentById(@PathParam("id") UUID id) throws Exception {
        return Response.ok(appointmentService.getAppointmentById(id)).build();
    }

    @GET
    @GetAllAppointment
    public Response getAllAppointments(){
        return Response.ok(appointmentService.getAllAppointments()).build();
    }

    @POST
    @CreateAppointment
    public Response createAppointment(AppointmentDto appointmentDto) throws Exception{
        appointmentService.createAppointment(appointmentDto);
        return Response.status(Response.Status.OK).entity("Turno medico creado exitosamente").build();
        //lanzar excepcion si medico/usuario/horario no disponible/encontrado
    }

    @DELETE
    @Path("/{id}")
    @DeleteAppointment
    //UUID como tipo de dato para id unicos en entidades JPA
    public Response deleteAppointment(@PathParam("id") UUID idUser, LocalTime consultingDate) throws Exception {
        appointmentService.deleteAppointment(idUser, consultingDate);
        return Response.status(Response.Status.OK).entity("Turno medico eliminado exitosamente").build();
    }

    @PUT
    @Path("/{id}")
    @UpdateAppointment
    public Response updateAppointment(@PathParam("id") UUID idAppointment, NewAppointmentDto newAppointmentDto) throws Exception{
        try {
            appointmentService.updateAppointment(idAppointment, newAppointmentDto);
            return Response.status(Response.Status.OK).entity("Turno médico actualizado exitosamente").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno del servidor").build();
        }
    }

}
