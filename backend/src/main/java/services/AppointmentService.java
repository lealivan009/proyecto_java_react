package services;

//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import models.Appointment;
import repositories.AppointmentRepository;
import java.util.UUID;

@Transactional
public class AppointmentService {

    //utilizo el repository que implementa a Panache y me proporciona los metodos 
    @Inject
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.listAll();
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    public Response createAppointment(Appointment appointment){
        appointmentRepository.persist(appointment); //metodo que me da Panache en el repository
        if(appointment.isPersistent()){
            return Response.created(URI.create("/appointments" + appointment.id)).build(); //devuelvo una respuesta con la uri del nuevo turno 
        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

