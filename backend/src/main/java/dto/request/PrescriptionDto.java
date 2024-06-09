package dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrescriptionDto(
    @NotBlank(message = "Patient name is required!") String patientName,
    @NotBlank(message = "Medication description is required!") String medicamentDesciption,
    @NotBlank(message = "Treatment steps are required!") String posology,
    @NotNull(message = "Number of days of treatment required!") 
    @Min(value = 1, message = "Value minimus is 1 day") 
    int durationTratament
) { }
