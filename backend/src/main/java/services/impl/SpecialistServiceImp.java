package services.impl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Medical;
import repositories.MedicalRepository;
import services.SpecialistService;

@ApplicationScoped
public class SpecialistServiceImp implements SpecialistService {
    @Inject
    private MedicalRepository medicalRepository;

    @Override
    public List<Medical> getAllSpecialists() {
        return medicalRepository.listAll(); 
    }
}
