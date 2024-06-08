package dto.request;


import jakarta.validation.constraints.NotBlank;

public record MedicalDtoRegister( 
    @NotBlank(message = "Fullname is needed!") String fullname,
    @NotBlank(message = "Matricula is needed!") String matricule,
    @NotBlank(message = "Consulting place is needed!") String consultingPlace,
    @NotBlank(message = "Medical Speciality is needed!") String medicalSpeciality,
    @NotBlank(message = "Cosulting dates is needed!") int[] startTime,
    @NotBlank(message = "Cosulting dates is needed!") int[] endTime
    )
{}