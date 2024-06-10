package dto.response;

import java.util.List;

import models.enumerations.SpecialityType;

public record SpecialistSchedulesDtoResponse(
    String fullname,
    SpecialityType specialityType,
    List<SchedulesDtoResponse> consultingDates,
    String consultingPlace
) {}
