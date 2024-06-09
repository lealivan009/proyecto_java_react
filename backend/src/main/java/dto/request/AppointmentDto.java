package dto.request;

import java.time.LocalTime;
import java.util.UUID;

//record para otra forma de definir clases pero estas contienen datos inmutables
public record AppointmentDto(String patient_name, String consultingReason, LocalTime consultingDate, UUID medicalId, UUID userId) {}
