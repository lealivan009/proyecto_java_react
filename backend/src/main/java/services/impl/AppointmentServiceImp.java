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
import repositories.AppointmentRepository;
import services.MedicalService;
import services.UserService;
import validator.Validator;
import services.AppointmentService;

import java.time.LocalDateTime;

import java.util.UUID;

import dto.request.AppointmentDto;
import dto.request.NewAppointmentDto;

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

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.listAll();
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public void createAppointment(AppointmentDto appointmentDto) throws Exception{
        //valido que los campos de appointment no vengan vacios
        validator.validate(appointmentDto);
        //busco que el usuario coincida con el usuario del turno y sino coincide ya larga la excepcion en el service
        userService.findUserById(appointmentDto.userId());
        Medical medical = medicalService.getMedicalById(appointmentDto.medicalId()); //idem

        //Obtener la lista de horarios de consulta del médico
        List<Schedules> consultingDates = medical.getConsultingDates();
        
        //Verificar si el horario del nuevo turno está dentro de algún horario de consulta del medico
        LocalTime newConsultingDate = appointmentDto.consultingDate(); //horario del nuevo turno
        boolean disponible = false;
        for (Schedules schedule : consultingDates) { //recorro la franja horaria de consultas del medico
            LocalTime startTime = schedule.getStartTime();
            LocalTime endTime = schedule.getEndTime();
            if (newConsultingDate.isAfter(startTime) && newConsultingDate.isBefore(endTime)) {
                disponible = true;
                break;
            }
        }
        
        //Si el horario del nuevo turno no está dentro de ningún horario de consulta, lanzar una excepción, sino crear el nuevo turno
        if (!disponible) {
            throw new RuntimeException("El horario del turno está fuera de los horarios de consulta del médico");
        }else{
            //Uso la clase mapper para pasar de dto a entidad
            Appointment appointment = AppointmentMapper.dtoToAppointment(appointmentDto);
            appointmentRepository.persist(appointment); //metodo que me da Panache en el repository para crear el nuevo turno en la bd
        }
    }

    @Transactional
    public void deleteAppointment(UUID idUser, LocalTime consultingDate){
        //Busco todos los turnos asociados al usuario que coincidan con la fecha y hora del turno pq sino me elimina todos los turnos de ese usuario
        List<Appointment> appointments = appointmentRepository.find("user.id = ?1 and consultingDate = ?2", idUser, consultingDate).list();

        // Verificar si el usuario tiene turnos asociados
        if (appointments.isEmpty()) {
            throw new RuntimeException("El usuario no tiene turnos médicos asociados");
        }
        // Iterar sobre cada turno del id de ese usuario
        for (Appointment appointment : appointments) {
            // Obtener la fecha del turno médico
            LocalDateTime appointmentDateTime = appointment.getConsultingDate().atDate(LocalDateTime.now().toLocalDate());

            // Verificar si el tiempo actual es mayor a un día antes del turno
            if (LocalDateTime.now().isAfter(appointmentDateTime)) {
                throw new RuntimeException("No se puede cancelar el turno médico porque ya ha pasado más de un día antes del turno");
            }

            //Metodo que me da Panache en el repository para eliminar el turno en la bd
            appointmentRepository.delete(appointment);
        }
    }

    public void updateAppointment(UUID idAppointment, NewAppointmentDto newAppointmentDto) throws Exception{
        //Obtener el turno médico a actualizar
        Appointment appointment = appointmentRepository.findById(idAppointment);
        
        // Verificar si el turno médico existe
        if (appointment == null) {
            throw new RuntimeException("El turno médico con ID " + idAppointment + " no existe");
        }
        
        // Verificar si hay cambios en la fecha y hora de la cita
        if (newAppointmentDto.consultingDate() != null) {
            // Verificar si la nueva fecha y hora de la cita están dentro de los horarios de consulta del médico
            Medical medical = appointment.getMedicalSpecialist();
            List<Schedules> consultingDates = medical.getConsultingDates();
            LocalTime updatedConsultingDate = newAppointmentDto.consultingDate();

            boolean disponible = false;
            for (Schedules schedule : consultingDates) {
                LocalTime startTime = schedule.getStartTime();
                LocalTime endTime = schedule.getEndTime();
                if (updatedConsultingDate.isAfter(startTime) && updatedConsultingDate.isBefore(endTime)) {
                    disponible = true;
                    break;
                }
            }
            if (!disponible) {
                throw new RuntimeException("El horario del turno está fuera de los horarios de consulta del médico");
            } else{
                // Actualizar la fecha y hora de la cita
                appointment.setConsultingDate(newAppointmentDto.consultingDate());
            }
        }

        // Verificar si hay cambios en el ID del médico especialista
        if (newAppointmentDto.medicalId() != null) {

            Medical medical = medicalService.getMedicalById(newAppointmentDto.medicalId());
            // Asignar el nuevo médico especialista al turno médico
            appointment.setMedicalSpecialist(medical);
        }

        // Verificar si hay cambios en el motivo de la consulta
        if (newAppointmentDto.consultingReason() != null) {
            // Actualizar el motivo de la consulta
            appointment.setConsultingReason(newAppointmentDto.consultingReason());
        }

        //metodo que me da Panache en el repository para persistir en la bd
        appointmentRepository.persist(appointment);
    }
}
