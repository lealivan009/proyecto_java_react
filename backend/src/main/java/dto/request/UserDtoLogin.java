package dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserDtoLogin(
    @NotBlank(message = "A email is needed!") String email,
    @NotBlank(message = "A password is needed!")String password) 
{}
