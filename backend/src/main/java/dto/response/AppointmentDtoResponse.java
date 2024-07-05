package dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record AppointmentDtoResponse(
    UUID id,
    DayOfWeek nameDay,
    String patientName,
    String consultingReason,
    LocalTime consultingDate
) {}
