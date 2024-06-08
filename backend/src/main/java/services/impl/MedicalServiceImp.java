package services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import dto.request.MedicalDtoRegister;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.MedicalMapper;
import models.Medical;
import models.Schedules;
import repositories.MedicalRepository;
import services.MedicalService;

@ApplicationScoped
public class MedicalServiceImp implements MedicalService{

    @Inject
    private MedicalRepository medicalRepo;

    @Transactional
    @Override
    public void registerAndSave(MedicalDtoRegister medicalDtoRegister) throws Exception {

        Medical medicalPersist = MedicalMapper.dtoToMedical(medicalDtoRegister);
        @SuppressWarnings("deprecation")
        Schedules schedules1 = new Schedules("Monday", new Date(2024, 6, 8, 12, 0,0),new Date(2024, 6, 8, 14, 0,0) );
        @SuppressWarnings("deprecation")
        Schedules schedules2 = new Schedules("Tuesday", new Date(2024, 6, 8, 15, 0,0),new Date(2024, 6, 8, 18, 0,0) );
        List<Schedules> scheduleslist = new ArrayList<Schedules>();
        scheduleslist.add(schedules1);
        scheduleslist.add(schedules2);
        medicalPersist.setConsultingDates(scheduleslist);
        
        medicalRepo.persist(medicalPersist);

    }

    @Override
    public Medical getUserById(UUID id) throws Exception {
        return medicalRepo.findByIdOptional(id).orElseThrow(() -> new Exception("Medical not found with id: " + id));
    }
    
}
