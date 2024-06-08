package dto.request;

//record para otra forma de definir clases pero estas contienen datos inmutables
public record AppointmentDto(String patient_name, String consultingReason) {}
