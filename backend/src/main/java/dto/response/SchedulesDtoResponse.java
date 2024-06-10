package dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record SchedulesDtoResponse(
    DayOfWeek nameDay,
    LocalTime startTime,
    LocalTime endTime
) {}
