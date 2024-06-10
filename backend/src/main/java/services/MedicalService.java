package services;

import java.util.List;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import dto.request.SchedulesDtoUpdate;
import dto.response.SpecialistSchedulesDtoResponse;
import models.Medical;

public interface MedicalService {

    /**
     * Registra y guarda un nuevo médico en la base de datos.
     * 
     * @param medicalDtoRegister DTO con los datos de registro del médico
     * @throws Exception si ocurre algún error durante el proceso de registro y guardado
     */
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws Exception;

    /**
     * Obtiene un médico por su ID desde la base de datos.
     * 
     * @param id ID del médico a buscar
     * @return el médico encontrado
     * @throws Exception si el médico no se encuentra en la base de datos
     */
    Medical getMedicalById(UUID id) throws Exception;

    public Object findAll();

    public void modifySchedules(UUID id, SchedulesDtoUpdate shceduleDto) throws Exception;

    public List<SpecialistSchedulesDtoResponse> getAllSpeciality();
}
