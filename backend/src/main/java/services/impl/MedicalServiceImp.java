package services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.response.FullMedicalUserDTO;
import dto.response.FullUserDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.MedicalMapper;
import models.Medical;
import models.Schedules;
import repositories.MedicalRepository;
import services.MedicalService;

@ApplicationScoped
public class MedicalServiceImp implements MedicalService {

    @Inject
    private MedicalRepository medicalRepo;

    /**
     * Registra y guarda un nuevo médico en la base de datos.
     * 
     * @param medicalDtoRegister DTO con los datos de registro del médico
     * @throws Exception si ocurre algún error durante el proceso de registro y guardado
     */
    @Transactional
    @Override
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws Exception {
        // Convierte el DTO a un objeto Medical
        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);

        // Crea dos horarios de consulta para el médico (ejemplo hardcodeado)
        Schedules schedules1 = new Schedules("Monday", new Date(2024, 6, 8, 12, 0, 0), new Date(2024, 6, 8, 14, 0, 0));
        Schedules schedules2 = new Schedules("Tuesday", new Date(2024, 6, 8, 15, 0, 0), new Date(2024, 6, 8, 18, 0, 0));

        // Agrega los horarios de consulta a una lista
        List<Schedules> scheduleslist = new ArrayList<>();
        scheduleslist.add(schedules1);
        scheduleslist.add(schedules2);

        // Asigna la lista de horarios de consulta al médico
        medicalPersist.setConsultingDates(scheduleslist);

        // Persiste el médico en la base de datos
        medicalRepo.persist(medicalPersist);
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

}
