package services.impl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import mapper.MedicalMapper;
import models.Medical;
import models.Schedules;
import repositories.MedicalRepository;
import services.MedicalService;

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
        validateMedical(medicalDtoRegister);
        // Convierte el DTO a un objeto Medical
        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);

        // Asigna la lista de horarios de consulta al médico
        medicalPersist.setConsultingDates(loadSchedules(medicalDtoRegister));

        // Persiste el médico en la base de datos
        medicalRepo.persist(medicalPersist);
    }

    private List<Schedules> loadSchedules(MedicalDtoRegister medicalDto) {
        List<Schedules> scheduleslist = new ArrayList<>(5);
        // startTime, endTime son arreglos int[hora,minuto]
        var startTime = LocalTime.of(medicalDto.startTime()[0], medicalDto.startTime()[1]);
        var endTime = LocalTime.of(medicalDto.endTime()[0], medicalDto.endTime()[1]);

        for (var day : DayOfWeek.values()) {
            // carga el mismo horario de lunes a viernes
            if (!day.equals(DayOfWeek.SUNDAY) && !day.equals(DayOfWeek.SATURDAY)) {
                scheduleslist.add(
                        new Schedules(day, startTime, endTime, true));
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
                .orElseThrow(() -> new Exception("Medical not found with id: " + id));
    }

    /**
     * Obtiene todos los médicos y los convierte en una lista de FullMedicalUserDTO.
     * 
     * @return lista de todos los médicos convertidos a FullMedicalUserDTO
     */
    public List<Medical> findAll() {
        return medicalRepo.listAll();
    }

    private void validateMedical(Object objMedical) throws Exception {
        var contrains = validator.validate(objMedical);
        if (!contrains.isEmpty()) {
            StringBuilder errorsMessage = new StringBuilder();
            contrains.stream()
                    .forEach(c -> errorsMessage.append(c.getMessageTemplate()).append(", "));
            throw new Exception(errorsMessage.toString());
        }

    }

}
