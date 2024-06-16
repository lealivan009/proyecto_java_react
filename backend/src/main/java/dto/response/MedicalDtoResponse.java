package dto.response;

import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;
import models.enumerations.SpecialityType;

@RegisterForReflection
public record MedicalDtoResponse(
        UUID id,
        String fullname,
        String matricule,
        String consultingPlace,
        SpecialityType specialityType
) {}
