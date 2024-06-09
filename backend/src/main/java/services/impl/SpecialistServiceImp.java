package services.impl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import models.enumerations.SpecialityType;
import services.SpecialistService;

@ApplicationScoped
public class SpecialistServiceImp implements SpecialistService {
    @Override
    public List<SpecialityType> getAllSpecialists() {
        return List.of(SpecialityType.values());
    }
}
