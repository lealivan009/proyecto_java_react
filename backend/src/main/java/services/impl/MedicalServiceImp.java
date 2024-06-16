package services.impl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import dto.response.SpecialistSchedulesDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.MedicalMapper;
import models.Medical;
import models.Schedules;
import models.enumerations.SpecialityType;
import repositories.MedicalRepository;
import services.MedicalService;
import validator.Validator;

@ApplicationScoped
public class MedicalServiceImp implements MedicalService {

    @Inject
    private MedicalRepository medicalRepo;

    @Inject
    Validator validator;

    /**
     * Registra y guarda un nuevo médico en la base de datos.
     * 
     * @param medicalDtoRegister DTO con los datos de registro del médico
     * @throws Exception si ocurre algún error durante el proceso de registro y
     *                   guardado
     */
    @Transactional
    @Override
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws Exception {
        //valido campos de medico
        validator.validate(medicalDtoRegister);
        // Convierte el DTO a un objeto Medical
        Optional<Medical> medical=medicalRepo.findByMatricule(medicalDtoRegister.matricule());
        
        // Validar que no exista otro medico con la misma matricula
        if(medical.isPresent()){
            throw new EntityAlredyExistException("This matricule exist!");
        }

        // Validar que el horario de finalizacion no sea menor al de inicio
        if (medicalDtoRegister.endTime().isBefore(medicalDtoRegister.startTime())) {
            throw new Exception("End time cannot be earlier than start time");
        }

        //Validar que exista especialidad
        try {
            SpecialityType.valueOf(medicalDtoRegister.medicalSpeciality().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Speciality does not exist!");
        }

        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);

        // Asigna la lista de horarios de consulta al médico
        medicalPersist.setConsultingDates(loadSchedules(medicalDtoRegister));

        // Persiste el médico en la base de datos
        medicalRepo.persist(medicalPersist);
    }

    @Transactional
    public void modifySchedules(UUID id, SchedulesDtoUpdate scheduleDto) throws Exception {
        Medical medical = getMedicalById(id);
    
        Schedules schedule = medical.getConsultingDates().stream()
            .filter(s -> s.getNameDay().equals(scheduleDto.nameDay()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("The schedule is not listed"));
    
        // Habilita/deshabilita, modifica el horario según el día
        schedule.setConsultingEnable(scheduleDto.consultingEnable());
    
        // Validación del horario
        if (scheduleDto.startTime() != null && scheduleDto.endTime() != null) {
            if (scheduleDto.endTime().isBefore(scheduleDto.startTime())) {
                throw new Exception("End time cannot be earlier than start time");
            }
        }
    
        if (scheduleDto.startTime() != null) {
            schedule.setStartTime(scheduleDto.startTime());
        }
        if (scheduleDto.endTime() != null) {
            schedule.setEndTime(scheduleDto.endTime());
        }
    
        // Remueve el horario viejo y asigna el horario nuevo
        medical.getConsultingDates().removeIf(s -> s.getNameDay().equals(scheduleDto.nameDay()));
        medical.getConsultingDates().add(schedule);
    }
    

    private List<Schedules> loadSchedules(MedicalDtoRegister medicalDto) {
        List<Schedules> scheduleslist = new ArrayList<>(5);

        // carga el mismo horario de lunes a viernes
        for (var day : DayOfWeek.values()) {
            if (!day.equals(DayOfWeek.SUNDAY) && !day.equals(DayOfWeek.SATURDAY)) {
                scheduleslist.add(
                    new Schedules(day,medicalDto.startTime(), medicalDto.endTime(), true));
            }
        }
        return scheduleslist;
    }

    /**
     * Obtiene un médico por su ID.
     * 
     * @param id ID del médico a buscar
     * @return el médico encontrado
     * @throws Exception si el médico no se encuentra en la base de datos
     */
    @Override
    public Medical getMedicalById(UUID id) throws Exception {
        return medicalRepo.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical not found with id: " + id));
    }

    /**
     * Obtiene todos los médicos y los convierte en una lista de FullMedicalUserDTO.
     * 
     * @return lista de todos los médicos convertidos a FullMedicalUserDTO
     */
    public List<Medical> findAll() {
        return medicalRepo.listAll();
    }

    //crea dtos de medico y horarios activos/disponibles
    @Override
    public List<SpecialistSchedulesDtoResponse> getAllSpeciality() {
        var medicals = findAll() ; 
        return medicals.stream().map(MedicalMapper::entityToDto).toList();
    } 
}
