package dto.response;

import java.util.UUID;
//Solo lo uso para recuperarlo de la base de datos y ver toda la info
public record FullUserDto(
    UUID id,
    String email,
    String password,
    String photo,
    String name,
    String lastname,
    String dni
) {}
