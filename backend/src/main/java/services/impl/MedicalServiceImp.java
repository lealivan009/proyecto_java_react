package services.impl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import dto.response.SpecialistSchedulesDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;
import exceptions.InvalidStartEndTimeException;
import exceptions.SpecialityNotExistException;
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

    
    @Transactional
    @Override
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws InvalidFieldException, EntityAlredyExistException, SpecialityNotExistException, InvalidStartEndTimeException {
        //valido campos de medico
        validator.validate(medicalDtoRegister);
        Optional<Medical> medical=medicalRepo.findByMatricule(medicalDtoRegister.matricule());
        
        // Validar que no exista otro medico con la misma matricula
        if(medical.isPresent()){
            throw new EntityAlredyExistException("This matricule exist!");
        }

        // Validar que el horario de finalizacion no sea menor al de inicio
        starTimeEndTimeValidate(medicalDtoRegister.startTime(), medicalDtoRegister.endTime());

        //Validar que exista especialidad
        try {
            SpecialityType.valueOf(medicalDtoRegister.medicalSpeciality().toUpperCase());
        } catch (Exception e) {
            throw new SpecialityNotExistException();
        }

        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);

        // Asigna la lista de horarios de consulta al médico
        medicalPersist.setConsultingDates(loadSchedules(medicalDtoRegister));

        // Persiste el médico en la base de datos
        medicalRepo.persist(medicalPersist);
    }

    @Transactional
    public void modifySchedules(UUID id, SchedulesDtoUpdate scheduleDto) throws EntityNotFoundException, InvalidStartEndTimeException {
        Medical medical = getMedicalById(id);
    
        Schedules schedule = medical.getConsultingDates().stream()
            .filter(s -> s.getNameDay().equals(scheduleDto.nameDay()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("The schedule is not listed"));
    
        
        // Validación del horario
        if (scheduleDto.startTime() != null && scheduleDto.endTime() != null) {
            starTimeEndTimeValidate(scheduleDto.startTime(), scheduleDto.endTime());
            schedule.setStartTime(scheduleDto.startTime());
            schedule.setEndTime(scheduleDto.endTime());
        }
        if (scheduleDto.startTime() != null) {
            starTimeEndTimeValidate(scheduleDto.startTime(), schedule.getEndTime());
            schedule.setStartTime(scheduleDto.startTime());
        }
        if (scheduleDto.endTime() != null) {
            starTimeEndTimeValidate(schedule.getStartTime(), scheduleDto.endTime());
            schedule.setEndTime(scheduleDto.endTime());
        }
        schedule.setConsultingEnable(scheduleDto.consultingEnable());
        
        // Remueve el horario viejo y asigna el horario nuevo
        medical.getConsultingDates().removeIf(s -> s.getNameDay().equals(scheduleDto.nameDay()));
        medical.getConsultingDates().add(schedule);
    }

    /**
     * Valida que el horario de comienzo no sea despues del de cierre
     * @param startTime horario de comienzo de turno
     * @param endTime horario de cierre del turno
     * @throws InvalidStartEndTimeException 
     */
    private void starTimeEndTimeValidate(LocalTime startTime, LocalTime endTime) throws InvalidStartEndTimeException {
        if(startTime.isAfter(endTime))
            throw new InvalidStartEndTimeException();
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

    @Override
    public Medical getMedicalById(UUID id) throws EntityNotFoundException  {
        return medicalRepo.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical not found with id: " + id));
    }

    public List<Medical> findAll() {
        return medicalRepo.listAll();
    }

    @Override
    public List<SpecialistSchedulesDtoResponse> getAllSpeciality() {
        var medicals = findAll(); 
        return medicals.stream().map(MedicalMapper::entityToDto).toList();
    } 
}
