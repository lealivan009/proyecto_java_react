package dto.response;

import java.util.List;

import models.Schedules;
import models.enumerations.SpecialityType;

public record ParcialSpecialistDto(
    String fullname,
    SpecialityType specialityType,
    List<Schedules> consultingDates,
    String consultingPlace
) {}
