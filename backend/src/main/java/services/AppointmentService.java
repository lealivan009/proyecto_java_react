package services;

import jakarta.transaction.Transactional;
import java.util.List;
import models.Appointment;
import java.util.UUID;
import dto.request.AppointmentDto;
import dto.request.NewAppointmentDto;
import dto.response.AppointmentDtoResponse;
import exceptions.AppointmentCancellationException;
import exceptions.ConsultationScheduleException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;
import exceptions.UserWithoutAppointmentException;

import java.time.LocalTime;

@Transactional
//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
public interface AppointmentService {

    /**
     * Obtiene todos los turnos medicos en base de datos
     * @return una List de appointment
     */
    public List<Appointment> getAllAppointments();

    /**
    * Obtiene un Appointment segun el id
     * @param id del tipo UUID 
     * @return un Appointment
     */
    public Appointment getAppointmentById(UUID id);

    public List<AppointmentDtoResponse> getAllByUser(UUID userId);

    /**
     * Modifica algun turno medico con 1 dia de anticipacion en la base de datos
     * @param appointmentDto del tipo NewAppointmentDto
     * @throws InvalidFieldException
     * @throws EntityNotFoundException
     * @throws ConsultationScheduleException
     */
    public void createAppointment(AppointmentDto appointmentDto) throws InvalidFieldException, EntityNotFoundException, ConsultationScheduleException ;

    /**
     * Borra un turno medico en ase de datos
     * @param idUser del tipo UUID de un User
     * @param consultingDate del tipo LocalTime 
     * @throws UserWithoutAppointmentException
     * @throws AppointmentCancellationException
     */
    public void deleteAppointment(UUID idUser, LocalTime consultingDate) throws UserWithoutAppointmentException, AppointmentCancellationException ;

    /**
     * Modifica un turno medico
     * @param idAppointment del tipo UUID
     * @param newAppointmentDto del tipo NewAppointmentDto en el cuerpo de la peticion
     * @throws EntityNotFoundException
     * @throws ConsultationScheduleException
     */
    public void updateAppointment(UUID idAppointment, NewAppointmentDto newAppointmentDto) throws EntityNotFoundException, ConsultationScheduleException ;

}

