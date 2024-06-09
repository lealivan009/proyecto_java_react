package dto.request;

import models.Appointment;
import models.enumerations.SpecialityType;

public record SpecialistDto(String name, SpecialityType specialityType, Appointment appointment) {
    
}
