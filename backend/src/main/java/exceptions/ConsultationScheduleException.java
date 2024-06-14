package exceptions;
/**
 * Indica que los horarios de consulta no concuerdan con los solicitados
 */
public class ConsultationScheduleException extends Exception {
    public ConsultationScheduleException() {
        super("The shift schedule is outside the doctor's office hours");
    }
}
