package services;

import java.util.List;

import jakarta.transaction.Transactional;
import models.Medical;
import services.SpecialistService;

@Transactional
public interface SpecialistService {
    public List<Medical> getAllSpecialists();
}
