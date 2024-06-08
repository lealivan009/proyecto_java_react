package mapper;

import dto.request.AppointmentDto;
import models.Appointment;

public class AppointmentMapper {

    public static Appointment dtoToAppointment(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .patientName(appointmentDto.patient_name())
                .consultingReason(appointmentDto.consultingReason())
                .consultingDate(appointmentDto.consultingDate()).build();
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
