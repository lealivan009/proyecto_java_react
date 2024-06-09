package dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDtoUpdate(
    String name, 
    String lastname, 
    @Pattern(regexp = "\\p{Alpha}*@\\p{Alpha}{2,}\\.\\p{Alpha}{2,4}", message = "Email not valid")
    String email,
    @Size(min = 8, message = "Password with minimum 8 characters") 
    String password, 
    @Pattern(regexp = "[0-9]*", message = "Only numbers without points") 
    String dni,
    String photo,
    LocalDate birthDate
    ) {
}
