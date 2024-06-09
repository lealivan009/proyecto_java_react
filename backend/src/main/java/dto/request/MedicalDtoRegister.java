package dto.request;


import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicalDtoRegister( 
    @NotBlank(message = "Fullname is needed!") String fullname,
    @NotBlank(message = "Matricula is needed!") String matricule,
    @NotBlank(message = "Consulting place is needed!") String consultingPlace,
    @NotBlank(message = "Medical Speciality is needed!") String medicalSpeciality,
    @NotNull(message = "Cosulting dates is needed!") LocalTime startTime,
    @NotNull(message = "Cosulting dates is needed!") LocalTime endTime
    )
{}