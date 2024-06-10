package dto.response;

import java.util.UUID;

public record FullMedicalDtoResponse(
        UUID id,
        String fullname,
        String matricule,
        String consultingPlace,
        String specialityType
) {}
