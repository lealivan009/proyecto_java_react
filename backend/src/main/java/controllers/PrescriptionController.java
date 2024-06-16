package controllers;

import java.util.UUID;

import config.annotations.PrescriptionSwaggerDocs;
import dto.request.PrescriptionDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import services.PrescriptionService;

@Path("/prescriptions")
public class PrescriptionController {

    @Inject
    PrescriptionService prescriptionService;
    
    @PrescriptionSwaggerDocs.SavePresciption
    @POST
    @Path("/{appointmentId}")
    public Response savePrescription(@PathParam("appointmentId") UUID appointmentId, PrescriptionDto prescriptionDto) throws Exception{
        prescriptionService.savePresciption(appointmentId, prescriptionDto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PrescriptionSwaggerDocs.GetPrescription
    @GET
    @Path("/{id}")
    public Response getPresciption(@PathParam("id") UUID id) throws Exception{
        return Response.ok(prescriptionService.getPrescription(id)).build();
    }
}
