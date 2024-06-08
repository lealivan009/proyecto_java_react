package mapper;

import dto.request.MedicalDtoRegister;
import models.Medical;
import models.SpecialityType;

public class MedicalMapper {
    
    public static Medical dtoToMedical(MedicalDtoRegister register) throws IllegalArgumentException {
        SpecialityType specialityType;
        try {
            specialityType = SpecialityType.valueOf(register.medicalSpeciality());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid medical speciality: " + register.medicalSpeciality());
        }

        return Medical.builder()
                .fullname(register.fullname())
                .consultingPlace(register.consultingPlace())
                .matricule(register.matricule())
                .specialityType(specialityType)
                .build();
    }
}
