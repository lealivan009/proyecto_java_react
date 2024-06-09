package dto.response;

import java.util.List;

import models.enumerations.SpecialityType;

public record SpecialistSchedulesDto(
    String fullname,
    SpecialityType specialityType,
    List<SchedulesDtoResponse> consultingDates,
    String consultingPlace
) { }
