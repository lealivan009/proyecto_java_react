package dto.request;



import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDtoUpdate(
    String name, 
    String lastname, 
    @Pattern(regexp = "\\p{Alpha}*@\\p{Alpha}{2,}\\.\\p{Alpha}{2,4}", message = "Email no valido") String email, 
    @NotBlank(message = "Una contraseña es obligatoria") @Size(min = 8, message = "Contraseña con minimo 8 caracteres") String password, 
    @Pattern(regexp = "[0-9]*", message = "Solo numeros sin punto") String dni,
    String photo,
    LocalDate birthDate
    ) {
}
