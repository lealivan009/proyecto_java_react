package dto.response;

import java.time.LocalDate;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PrescriptionDtoResponse(
    UUID id,
    String patientName,
    String medicamentDesciption,
    String posology,
    int durationTratament,
    LocalDate emissionDate
) {}
