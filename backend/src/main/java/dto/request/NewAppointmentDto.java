package dto.request;

import java.time.LocalTime;
import java.util.UUID;

//nuevo dto para campos especificos obligatorios para una actualizacion del turno
public record NewAppointmentDto(
    String consultingReason,
    LocalTime consultingDate,
    UUID medicalId
) {}