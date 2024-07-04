package mapper;

import java.time.LocalDate;

import dto.request.PrescriptionDto;
import dto.response.PrescriptionDtoResponse;
import models.Prescription;

public class PrescriptionMapper {

    public static Prescription dtoToEntity(PrescriptionDto dto){
        return Prescription.builder()
            .patientName(dto.patientName())
            .medicamentDescription(dto.medicamentDesciption())
            .posology(dto.posology())
            .durationTratament(dto.durationTratament())
            .emissionDate(LocalDate.now())
            .build();
    }

    public static PrescriptionDtoResponse entityToDto(Prescription entity) {
        return new PrescriptionDtoResponse(
            entity.getId(),
            entity.getPatientName(), 
            entity.getMedicamentDescription(), 
            entity.getPosology(), 
            entity.getDurationTratament(),
            entity.getEmissionDate()
        );
    }
    
}
