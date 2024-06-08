package dto.response;

import java.util.UUID;

public record FullMedicalUserDTO(
        UUID id,
        String fullname,
        String matricule,
        String consultingPlace,
        String specialityType) {

}
