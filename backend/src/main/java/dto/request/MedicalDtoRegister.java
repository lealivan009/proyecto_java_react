package dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MedicalDtoRegister( 
    @NotBlank(message = "Fullname is needed!") String fullname,
    @NotBlank(message = "Matricula is needed!") String matricule,
    @NotBlank(message = "Consulting place is needed!") String consultingPlace,
    @NotBlank(message = "Medical Speciality is needed!") String medicalSpeciality,
    @NotEmpty(message = "Cosulting dates is needed!") int[] startTime,
    @NotEmpty(message = "Cosulting dates is needed!") int[] endTime
    )
{}