package services;

import java.util.UUID;

import dto.request.MedicalDtoRegister;
import models.Medical;

public interface MedicalService {
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws Exception;
    Medical getMedicalById(UUID id) throws Exception;
}
