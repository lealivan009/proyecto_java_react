package dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record UserDtoResponse(
    UUID id,
    String email,
    String password,
    String photo,
    String name,
    String lastname,
    String dni,
    LocalDate birthName
) {}
