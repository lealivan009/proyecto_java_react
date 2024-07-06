import { DayOfWeek } from "./enums/days.models"

interface AbstractSchedule {
    nameDay: DayOfWeek,
    startTime: Date,
    endTime: Date,
}

export interface UpdateSchedule extends AbstractSchedule {
    consultingEnable: boolean
}

export interface Schedule extends AbstractSchedule {
    id: string
}