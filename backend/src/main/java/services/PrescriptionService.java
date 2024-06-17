package services;

import java.util.UUID;

import dto.request.PrescriptionDto;
import dto.response.PrescriptionDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;

public interface PrescriptionService {

    /**
     * Agrega una prescripcion en base de datos segun el turno medico
     * @param appointmentId del tipo UUID
     * @param prescriptionDto del tipo PrescriptionDto donde vienen campos obligatorios
     * @throws InvalidFieldException
     * @throws EntityAlredyExistException
     */
    public void savePresciption(UUID appointmentId, PrescriptionDto prescriptionDto) throws InvalidFieldException, EntityAlredyExistException;

    /**
     * Obtiene una prescripcion segun el id
     * @param prescriptionId del tipo UUID
     * @return una Prescription 
     * @throws EntityNotFoundException
     */
    public PrescriptionDtoResponse getPrescription(UUID prescriptionId) throws EntityNotFoundException ;
}
