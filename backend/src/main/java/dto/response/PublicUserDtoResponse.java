package dto.response;

import java.time.LocalDate;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PublicUserDtoResponse(
    UUID id,
    String email,
    String photo,
    String name,
    String lastname,
    String dni,
    LocalDate birthDate
) {}
