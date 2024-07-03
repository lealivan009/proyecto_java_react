package dto.response;

import java.time.LocalTime;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record AppointmentDtoResponse(
    UUID id,
    String patientName,
    String consultingReason,
    LocalTime consultingDate
) {}
