package dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDtoRegister(
    @NotBlank(message = "El email es obligatorio") String email,
    @NotBlank(message = "Una contraseña es obligatoria") @Size(min = 8, message = "Contraseña con minimo 8 caracteres") String password,
    @NotBlank(message = "Su nombre es obligatorio") String name,
    @NotBlank(message = "Su apellido es obligatorio") String lastname,
    @NotBlank(message = "El numero de dni es obligatorio") String dni,
    String photo,
    int age
) {}
