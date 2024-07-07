import { SpecialityEnum } from "./enums/speciality.models";
import { Schedule } from "./schedule.models";

interface AbstractMedical {
    fullname: string,
    matricule: string,
    consultingPlace: string,
    medicalSpeciality: SpecialityEnum,
}

export interface Medical extends AbstractMedical{
    id: string
}

export interface RegisterMedical extends AbstractMedical{
    startTime: Date,
    endTime: Date,
}

export interface SpecialistSchedules extends AbstractMedical {
    id: string,
    consultingDates: Array<Schedule>
}
