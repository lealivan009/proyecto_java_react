package dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserDtoLogin(
    @NotBlank(message = "Un email es necesario") String email,
    @NotBlank(message = "Una contraseña es necesaria")String password) 
{}
