package services.impl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import dto.response.ParcialSpecialistDto;
import dto.response.SpecialistSchedulesDtoResponse;
import exceptions.EntityNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.MedicalMapper;
import mapper.SchedulesMapper;
import models.Medical;
import models.Schedules;
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
        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);

        // Asigna la lista de horarios de consulta al médico
        medicalPersist.setConsultingDates(loadSchedules(medicalDtoRegister));

        // Persiste el médico en la base de datos
        medicalRepo.persist(medicalPersist);
    }

    @Transactional
    public void modifySchedules(UUID id, SchedulesDtoUpdate scheduleDto) throws Exception{
        Medical medical = getMedicalById(id);

        Schedules schedule = medical.getConsultingDates().stream()
            .filter(s -> s.getNameDay().equals(scheduleDto.nameDay()))
            .findFirst().orElseThrow(()-> new EntityNotFoundException("The schedule is not listed"));
    
        // habilita/desabilita, modifica el horario segun el dia
        schedule.setConsultingEnable(scheduleDto.consultingEnable());
        if(scheduleDto.startTime() != null) 
            schedule.setStartTime(scheduleDto.startTime());
        if(scheduleDto.endTime() != null ) 
            schedule.setEndTime(scheduleDto.endTime());
        //remueve el horario viejo y asigna el horario nuevo
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

    //crea dtos de medico y horarios disponibles
    @Override
    public List<SpecialistSchedulesDtoResponse> getAllSpeciality() {
        List<SpecialistSchedulesDtoResponse> specialists = new ArrayList<>();
        var medicals = findAll() ; 

        medicals.stream().forEach(
            medical -> {
                //dto intermedio de specialist
                ParcialSpecialistDto medicalDto =  MedicalMapper.entityToDto(medical);
                specialists.add(
                    //dto completo medico con horario disponible
                    new SpecialistSchedulesDtoResponse(
                        medicalDto.fullname(), 
                        medicalDto.specialityType(),
                        medicalDto.consultingDates().stream()
                            .filter(s -> s.isConsultingEnable())
                            .map(s -> SchedulesMapper.entityToDto(s)).toList(),
                        medicalDto.consultingPlace()
                    )
                );
            }
        );
        return specialists;
    }

}
