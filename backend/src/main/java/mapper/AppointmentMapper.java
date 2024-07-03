package mapper;

import dto.request.AppointmentDto;
import dto.response.AppointmentDtoResponse;
import models.Appointment;
import models.Medical;
import models.User;

public class AppointmentMapper {

    public static Appointment dtoToAppointment(AppointmentDto appointmentDto, User user, Medical medical) {
        return Appointment.builder()
                .patientName(appointmentDto.patient_name())
                .consultingReason(appointmentDto.consultingReason())
                .consultingDate(appointmentDto.consultingDate())
                .user(user)
                .medicalSpecialist(medical)
                .build();
    }

    public static AppointmentDtoResponse entityToDtoResponse(Appointment entity){
        return new AppointmentDtoResponse(
            entity.getId(),
            entity.getPatientName(),
            entity.getConsultingReason(),
            entity.getConsultingDate()
        );
    }

    public static AppointmentDto appointmentToDto(Appointment appointment) {
        return new AppointmentDto(
            appointment.getPatientName(),
            appointment.getConsultingReason(),
            appointment.getConsultingDate(),
            appointment.getMedicalSpecialist().getId(),
            appointment.getUser().getId()
        );
    }

}
