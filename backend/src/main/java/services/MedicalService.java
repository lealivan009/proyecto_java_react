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
     *                 
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

    /**
     * Obtiene todos los médicos y los convierte en una lista de FullMedicalUserDTO.
     * 
     * @return lista de todos los médicos convertidos a FullMedicalUserDTO
     */
    public Object findAll();

    /**
     * Metodo que modifica los horarios de cada Medical
     * @param id ID del Medical para modificar sus horarios
     * @param shceduleDto cuerpo de la peticion que puede venir con el dia del horario, hora de inicio/final y si quiere desabilitarlo. 
     * @throws Exception Puede lanzar una exception si los horarios son erroneos, los dias no existen (Lunes - Viernes), el Medical no se encuentra.
     */
    public void modifySchedules(UUID id, SchedulesDtoUpdate shceduleDto) throws Exception;

    /**
     * Devuelve una lista DTO de Medical con su especialidad y horarios habilitados
     * @return List SpecialistSchedulesDtoResponse
     */
    public List<SpecialistSchedulesDtoResponse> getAllSpeciality();
}
