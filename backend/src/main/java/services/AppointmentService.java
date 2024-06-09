package services;

import jakarta.transaction.Transactional;
import java.util.List;
import models.Appointment;
import java.util.UUID;
import dto.request.AppointmentDto;
import dto.request.NewAppointmentDto;

import java.time.LocalTime;

@Transactional
//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
public interface AppointmentService {

    public List<Appointment> getAllAppointments();

    public Appointment getAppointmentById(UUID id);

    public void createAppointment(AppointmentDto appointmentDto) throws Exception;

    public void deleteAppointment(UUID idUser, LocalTime consultingDate);

    public void updateAppointment(UUID idAppointment, NewAppointmentDto newAppointmentDto) throws Exception;

}

