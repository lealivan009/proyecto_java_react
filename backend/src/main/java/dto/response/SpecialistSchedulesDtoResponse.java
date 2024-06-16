package dto.response;

import java.util.List;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;
import models.enumerations.SpecialityType;

@RegisterForReflection
public record SpecialistSchedulesDtoResponse(
    UUID id,
    String fullname,
    SpecialityType specialityType,
    List<SchedulesDtoResponse> consultingDates,
    String consultingPlace
) {}
