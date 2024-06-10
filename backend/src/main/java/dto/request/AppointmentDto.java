package dto.request;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//record para otra forma de definir clases pero estas contienen datos inmutables
public record AppointmentDto(
    @NotBlank(message = "Name the patient is mandatory!") String patient_name,
    @NotBlank(message = "A consulting reason mandatory!")String consultingReason, 
    @NotNull(message = "Cosulting date is needed!") LocalTime consultingDate, 
    UUID medicalId, 
    UUID userId
) {}
