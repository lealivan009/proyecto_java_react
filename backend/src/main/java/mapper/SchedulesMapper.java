package mapper;

import dto.response.SchedulesDtoResponse;
import models.Schedules;

public class SchedulesMapper {
    public static SchedulesDtoResponse entityToDto(Schedules entity){
        return new SchedulesDtoResponse(
            entity.getNameDay(),
            entity.getStartTime(),
            entity.getEndTime()
        );
    }
}
