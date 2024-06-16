package services.impl;

import java.util.UUID;

import dto.request.PrescriptionDto;
import dto.response.PrescriptionDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidFieldException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.PrescriptionMapper;
import models.Prescription;
import repositories.PrescriptionRepository;
import services.AppointmentService;
import services.PrescriptionService;
import validator.Validator;

@ApplicationScoped
public class PrescriptionServiceImp implements PrescriptionService {

    @Inject
    AppointmentService appointmentService;
    
    @Inject
    PrescriptionRepository prescriptionRepo;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public void savePresciption(UUID appointmentId, PrescriptionDto prescriptionDto) throws InvalidFieldException, EntityAlredyExistException {
        validator.validate(prescriptionDto);
        var prescriptionToPersist = PrescriptionMapper.dtoToEntity(prescriptionDto);
        var appointment = appointmentService.getAppointmentById(appointmentId);

        if(appointment.getPrescription() != null)
            throw new EntityAlredyExistException("There is already a prescription for this medical appointment");

        appointment.setPrescription(prescriptionToPersist);
        prescriptionRepo.persist(prescriptionToPersist);
    }
    
    @Override
    public PrescriptionDtoResponse getPrescription(UUID prescriptionId) throws EntityNotFoundException {
        Prescription pres = prescriptionRepo.findByIdOptional(prescriptionId)
            .orElseThrow(()-> new EntityNotFoundException("Prescription not found with id "+ prescriptionId.toString()));
        return PrescriptionMapper.entityToDto(pres);
    }

}
