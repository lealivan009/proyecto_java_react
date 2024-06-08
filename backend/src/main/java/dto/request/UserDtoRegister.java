package dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDtoRegister(
    @NotBlank(message = "El email es obligatorio") 
    @Pattern(regexp = "\\p{Alpha}*@\\p{Alpha}{2,}\\.\\p{Alpha}{2,4}", message = "Email no valido") 
    String email,
    @NotBlank(message = "Una contraseña es obligatoria") 
    @Size(min = 8, message = "Contraseña con minimo 8 caracteres") 
    String password,
    @NotBlank(message = "Su nombre es obligatorio") String name,
    @NotBlank(message = "Su apellido es obligatorio") String lastname,
    @NotBlank(message = "El numero de dni es obligatorio") 
    @Pattern(regexp = "[0-9]*", message = "Solo numeros sin punto") 
    String dni,
    LocalDate birthDate,
    String photo
) {}
