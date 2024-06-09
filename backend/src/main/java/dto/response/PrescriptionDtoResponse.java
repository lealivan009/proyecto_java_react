package dto.response;

import java.time.LocalDate;

public record PrescriptionDtoResponse(
    String patientName,
    String medicamentDesciption,
    String posology,
    int durationTratament,
    LocalDate emissionDate
) { }
