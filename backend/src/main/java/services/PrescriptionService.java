package services;

import java.util.UUID;

import dto.request.PrescriptionDto;
import dto.response.PrescriptionDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;

public interface PrescriptionService {
    public void savePresciption(UUID appointmentId, PrescriptionDto prescriptionDto) throws InvalidFieldException, EntityAlredyExistException ;
    public PrescriptionDtoResponse getPrescription(UUID prescriptionId) throws EntityNotFoundException ;
}
