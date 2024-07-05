package services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.AppointmentMapper;

import java.time.LocalTime;
import java.util.List;

import models.Appointment;
import models.Medical;
import models.Schedules;
import models.User;
import repositories.AppointmentRepository;
import services.MedicalService;
import services.UserService;
import validator.Validator;
import services.AppointmentService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.util.UUID;

import dto.request.AppointmentDto;
import dto.request.NewAppointmentDto;
import dto.response.AppointmentDtoResponse;
import exceptions.AppointmentCancellationException;
import exceptions.ConsultationScheduleException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;
import exceptions.UserWithoutAppointmentException;

@ApplicationScoped
//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
public class AppointmentServiceImp implements AppointmentService{

    //utilizo el repository que implementa a Panache y me proporciona los metodos 
    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    UserService userService;

    @Inject
    MedicalService medicalService; 

    @Inject
    Validator validator;

    public AppointmentServiceImp(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.listAll();
    }

    @Override
    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public void createAppointment(AppointmentDto appointmentDto) throws InvalidFieldException, EntityNotFoundException, ConsultationScheduleException{
        //valido que los campos de appointment no vengan vacios
        validator.validate(appointmentDto);

        User user = userService.findUserById(appointmentDto.userId());
        Medical medical = medicalService.getMedicalById(appointmentDto.medicalId()); 

        Schedules consultingDate = getScheduleOfMedicalByDay(medical, appointmentDto.nameDay());

        validateShcedule(appointmentDto.consultingDate(), consultingDate);
        
        Appointment appointment = AppointmentMapper.dtoToAppointment(appointmentDto, user, medical);
        appointmentRepository.persist(appointment);
    }

    @Transactional
    public void deleteAppointment(UUID idUser, LocalTime consultingDate) throws UserWithoutAppointmentException, AppointmentCancellationException {
        //Busco todos los turnos asociados al usuario que coincidan con la fecha y hora del turno pq sino me elimina todos los turnos de ese usuario
        List<Appointment> appointments = appointmentRepository.find("user.id = ?1 and consultingDate = ?2", idUser, consultingDate).list();

        // Verificar si el usuario tiene turnos asociados
        if (appointments.isEmpty()) {
            throw new UserWithoutAppointmentException("User does not have associated medical appointments");
        }
        // Iterar sobre cada turno del id de ese usuario
        for (Appointment appointment : appointments) {
            // Obtener la fecha del turno médico
            LocalDateTime appointmentDateTime = appointment.getConsultingDate().atDate(LocalDateTime.now().toLocalDate());

            // Verificar si el tiempo actual es mayor a un día antes del turno
            if (LocalDateTime.now().isAfter(appointmentDateTime)) {
                throw new AppointmentCancellationException("The medical appointment cannot be canceled because more than one day has already passed before the appointment");
            }

            //Metodo que me da Panache en el repository para eliminar el turno en la bd
            appointmentRepository.delete(appointment);
        }
    }

    @Transactional()
    public void updateAppointment(UUID idAppointment, NewAppointmentDto newAppointmentDto) throws EntityNotFoundException, ConsultationScheduleException {
        //Obtener el turno médico a actualizar
        Appointment appointment = appointmentRepository.findByIdOptional(idAppointment)
            .orElseThrow(()->new EntityNotFoundException("Medical appointment not found with id " + idAppointment));
  
        
        // Verificar si hay cambios en la fecha y hora de la cita
        if (newAppointmentDto.consultingDate() != null) {

            Schedules schedule =  getScheduleOfMedicalByDay(appointment.getMedicalSpecialist(), newAppointmentDto.nameDay());

            validateShcedule(newAppointmentDto.consultingDate(), schedule);
            
            appointment.setConsultingDate(newAppointmentDto.consultingDate());
        }

        // Verificar si hay cambios en el ID del médico especialista
        if (newAppointmentDto.medicalId() != null) {

            Medical medical = medicalService.getMedicalById(newAppointmentDto.medicalId());
            Schedules schedule = getScheduleOfMedicalByDay(medical, newAppointmentDto.nameDay());

            validateShcedule(appointment.getConsultingDate(), schedule);

            appointment.setMedicalSpecialist(medical);
        }

        // Verificar si hay cambios en el motivo de la consulta
        if (newAppointmentDto.consultingReason() != null) {
            // Actualizar el motivo de la consulta
            appointment.setConsultingReason(newAppointmentDto.consultingReason());
        }
    
        appointment.setNameDay(newAppointmentDto.nameDay());
        appointmentRepository.persist(appointment);
    }

    private void validateShcedule(LocalTime newDateConsulting, Schedules consultingDate) throws ConsultationScheduleException{
        if (!newDateConsulting.isAfter(consultingDate.getStartTime()) ||  !newDateConsulting.isBefore(consultingDate.getEndTime()))  
            throw new ConsultationScheduleException();
    }

    private Schedules getScheduleOfMedicalByDay(Medical medical, DayOfWeek day){
        return medical.getConsultingDates()
            .stream()
            .filter(s -> s.getNameDay().equals(day))
            .findFirst().get();
    }

    public List<AppointmentDtoResponse> getAllByUser(UUID userId) {
       return appointmentRepository.getAllByUser(userId);
    }
}
