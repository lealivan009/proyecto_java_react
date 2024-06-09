package dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;

public record SchedulesDtoUpdate(
    @NotBlank(message = "A day of week is needed!") 
    DayOfWeek nameDay, 
    LocalTime startTime, 
    LocalTime endTime,
    boolean consultingEnable
) {}
