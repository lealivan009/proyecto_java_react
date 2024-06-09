package services;

import java.util.List;

import jakarta.transaction.Transactional;
import models.enumerations.SpecialityType;
import services.SpecialistService;

@Transactional
public interface SpecialistService {
    public List<SpecialityType> getAllSpecialists();
}
