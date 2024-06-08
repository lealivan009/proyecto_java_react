package services.impl;

import jakarta.enterprise.context.ApplicationScoped;
//capa de servicio que interactua con la capa de persistencia (repositorios JPA)
import jakarta.inject.Inject;
import mapper.AppointmentMapper;

import java.util.Date;
import java.util.List;

import models.Appointment;
import models.Schedules;
import repositories.AppointmentRepository;
import services.UserService;

import java.util.UUID;

import dto.request.AppointmentDto;

@ApplicationScoped
public class AppointmentServiceImp {

    //utilizo el repository que implementa a Panache y me proporciona los metodos 
    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    UserService userService;
    /*@Inject
    MedicalService medicalService; */

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.listAll();
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    public void createAppointment(AppointmentDto appointmentDto){
        //busco que el usuario coincida con el usuario del turno y sino coincide ya larga la excepcion en el service
        userService.findUserById(appointmentDto.userId());
        medicalService.findMedicalById(appointmentDto.medicalId()); //idem

        // Obtener la lista de horarios de consulta del médico
        List<Schedules> consultingDates = medicalService.getConsultingDates();
        
        // Verificar si el horario del nuevo turno está dentro de algún horario de consulta del medico
        Date newConsultingDate = appointmentDto.consultingDate(); //horario del nuevo turno
        boolean disponible = false;
        for (Schedules schedule : consultingDates) { //recorro la franja horaria de consultas del medico
            Date startTime = schedule.getStartTime();
            Date endTime = schedule.getEndTime();
            if (newConsultingDate.after(startTime) && newConsultingDate.before(endTime)) {
                disponible = true;
                break;
            }
        }
        
        // Si el horario del nuevo turno no está dentro de ningún horario de consulta, lanzar una excepción, sino crear el nuevo turno
        if (!disponible) {
            throw new RuntimeException("El horario del turno está fuera de los horarios de consulta del médico");
        }else{
            //uso la clase mapper para pasar de dto a entidad
            Appointment appointment = AppointmentMapper.dtoToAppointment(appointmentDto);
            appointmentRepository.persist(appointment); //metodo que me da Panache en el repository para crear el nuevo turno
        }
    }
}
