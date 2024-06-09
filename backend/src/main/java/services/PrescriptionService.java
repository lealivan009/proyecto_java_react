package services;

import java.util.UUID;

import dto.request.PrescriptionDto;
import dto.response.PrescriptionDtoResponse;

public interface PrescriptionService {
    public void savePresciption(UUID appointmentId, PrescriptionDto prescriptionDto) throws Exception;
    public PrescriptionDtoResponse getPrescription(UUID prescriptionId) throws Exception;
}
