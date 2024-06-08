package services;

import jakarta.enterprise.context.ApplicationScoped;
//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
import jakarta.inject.Inject;

import java.util.List;

import models.Appointment;
import repositories.AppointmentRepository;
import java.util.UUID;

@ApplicationScoped
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
}

