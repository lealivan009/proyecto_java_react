package dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDtoRegister(
    @NotBlank(message = "Email is mandatory!") 
    @Pattern(regexp = "\\p{Alpha}*@\\p{Alpha}{2,}\\.\\p{Alpha}{2,4}", message = "Email not valid") 
    String email,
    @NotBlank(message = "Password is mandatory") 
    @Size(min = 8, message = "Password with minimum 8 characters") 
    String password,
    String repeatPassword,
    @NotBlank(message = "Name is mandatory")
    String name,
    @NotBlank(message = "Lastname is mandatory")
    String lastname,
    @NotBlank(message = "Dni number is mandatory") 
    @Pattern(regexp = "[0-9]*", message = "Only numbers without points") 
    String dni,
    LocalDate birthDate,
    String photo
) {}
