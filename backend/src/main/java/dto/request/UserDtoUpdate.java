package dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;

public record UserDtoUpdate(
    String name, 
    String lastname, 
    @Pattern(regexp = "\\p{Alpha}*@\\p{Alpha}{2,}\\.\\p{Alpha}{2,4}", message = "Email not valid")
    String email, 
    String password, 
    @Pattern(regexp = "[0-9]*", message = "Only numbers without points") 
    String dni,
    String photo,
    LocalDate birthDate
    ) {
}
