package dto.request;

import java.time.DayOfWeek;

import jakarta.validation.constraints.NotBlank;

public record SchedulesDtoUpdate(
    @NotBlank(message = "A day of week is needed!") 
    DayOfWeek nameDay, 
    int[] startTime, 
    int[] endTime,
    boolean consultingEnable
) {}
