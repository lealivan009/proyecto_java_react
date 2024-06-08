package services;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import models.Appointment;
import java.util.UUID;

@ApplicationScoped
public interface AppointmentService {
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(UUID id);
    public void createAppointment(Appointment appointment) throws Exception;
}

