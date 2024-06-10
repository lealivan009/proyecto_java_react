package dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
//Solo lo uso para recuperarlo de la base de datos y ver toda la info
public record FullUserDtoResponse(
    UUID id,
    String email,
    String password,
    String photo,
    String name,
    String lastname,
    String dni,
    LocalDate birthDate,
    boolean isEnable,
    LocalDateTime createdUser,
    LocalDateTime updateUser
) {}
