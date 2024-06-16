package mapper;

import dto.request.MedicalDtoRegister;
import dto.response.SpecialistSchedulesDtoResponse;
import models.Medical;
import models.enumerations.SpecialityType;

public class MedicalMapper {
    
    /**
     * Convierte un DTO de registro de médico a un objeto Medical.
     * 
     * @param register DTO de registro de médico
     * @return Objeto Medical creado a partir del DTO
     * @throws Exception si la especialidad médica no es válida
     */
    public static Medical dtoToMedical(MedicalDtoRegister register) throws Exception {
        SpecialityType specialityType;
        try {
            // Intenta obtener el tipo de especialidad médica desde el DTO
            specialityType = SpecialityType.valueOf(register.medicalSpeciality());
        } catch (Exception e) {
            // Si ocurre un error al obtener el tipo de especialidad, lanza una excepción
            throw new Exception("Invalid medical speciality: " + register.medicalSpeciality());
        }

        // Crea y devuelve un objeto Medical utilizando el constructor builder
        return Medical.builder()
                .fullname(register.fullname()) // Asigna el nombre completo del médico
                .consultingPlace(register.consultingPlace()) // Asigna el lugar de consulta del médico
                .matricule(register.matricule()) // Asigna la matrícula del médico
                .specialityType(specialityType) // Asigna el tipo de especialidad del médico
                .build();
    }

    //mappea un dto de medico con los horarios activos
    public static SpecialistSchedulesDtoResponse entityToDto(Medical entity){
        return new SpecialistSchedulesDtoResponse(
            entity.getId(),
            entity.getFullname(),
            entity.getSpecialityType(),
            entity.getConsultingDates().stream()
                .filter(s -> s.isConsultingEnable())
                .map(SchedulesMapper::entityToDto).toList(),
            entity.getConsultingPlace()
        );
    }
}
